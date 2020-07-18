call runcrud
if "%ERRORLEVEL%" == "0" goto startChrome
echo.
echo runcrud has errors - breaking work
goto fail

:startChrome
start chrome.exe "http://localhost:8080/crud/v1/task/getTasks"
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Showtasks is finished.