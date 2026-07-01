$port = 8081
$localPath = "d:\Jinara Arts"
$listener = New-Object System.Net.HttpListener
$listener.Prefixes.Add("http://localhost:$port/")

Write-Host "=============================================" -ForegroundColor Yellow
Write-Host "  Jinara Arts Local Server starting..." -ForegroundColor Yellow
Write-Host "  Hosting: $localPath" -ForegroundColor White
Write-Host "  URL: http://localhost:$port/" -ForegroundColor Green
Write-Host "  Press Ctrl+C in terminal to stop server." -ForegroundColor Yellow
Write-Host "=============================================" -ForegroundColor Yellow

try {
    $listener.Start()
    while ($listener.IsListening) {
        $context = $listener.GetContext()
        $request = $context.Request
        $response = $context.Response
        
        # Get requested file path
        $urlPath = $request.Url.LocalPath
        if ($urlPath -eq "/") { $urlPath = "/index.html" }
        $filePath = Join-Path $localPath $urlPath
        
        if (Test-Path $filePath -PathType Leaf) {
            # Set Content-Type
            $ext = [System.IO.Path]::GetExtension($filePath).ToLower()
            $contentType = "text/html"
            if ($ext -eq ".css") { $contentType = "text/css" }
            elseif ($ext -eq ".js") { $contentType = "application/javascript" }
            elseif ($ext -eq ".png") { $contentType = "image/png" }
            elseif ($ext -eq ".jpg" -or $ext -eq ".jpeg") { $contentType = "image/jpeg" }
            elseif ($ext -eq ".svg") { $contentType = "image/svg+xml" }
            elseif ($ext -eq ".ico") { $contentType = "image/x-icon" }
            elseif ($ext -eq ".mp4") { $contentType = "video/mp4" }
            
            $response.ContentType = $contentType
            
            # Read file content and write to response stream
            $bytes = [System.IO.File]::ReadAllBytes($filePath)
            $response.ContentLength64 = $bytes.Length
            $response.OutputStream.Write($bytes, 0, $bytes.Length)
            
            Write-Host "[200] Served: $urlPath ($contentType)" -ForegroundColor Green
        } else {
            $response.StatusCode = 404
            $response.StatusDescription = "Not Found"
            $bytes = [System.Text.Encoding]::UTF8.GetBytes("404 - File Not Found")
            $response.ContentLength64 = $bytes.Length
            $response.OutputStream.Write($bytes, 0, $bytes.Length)
            
            Write-Host "[404] Not Found: $urlPath" -ForegroundColor Red
        }
        $response.Close()
    }
} catch {
    Write-Host "Error starting server: $_" -ForegroundColor Red
} finally {
    $listener.Stop()
    Write-Host "Server stopped." -ForegroundColor Yellow
}
