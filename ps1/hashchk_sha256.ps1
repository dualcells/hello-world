<#
.SYNOPSIS
    Provide a username to retrieve information from Active Directory
.DESCRIPTION
    Active Directory user records include an ObjectGUID that is converted
    into Base64String for use in ServiceNow. Executing the script, providing
    a username, e.g. first.last, will return a result.
.NOTES
    File Name      : hashchk_sha256.ps1
    Author         : dualcells
    Prerequisites  : None
.LINK
    https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.utility/get-filehash?view=powershell-7.4

#>


# Example:
# 8f5138fecb53c08c20abd4fa6812f9400051f3852582a2142ffda0dff73a5824
# C:\Users\dualcells\Downloads\openjdk-22_windows-x64_bin.zip

try {
    $MyInputHash = Read-Host -Prompt "Input 1: SHA256"
    $MyInputFile = Read-Host -Prompt "Input 2: File"
    $Calculation = Get-FileHash $MyInputFile -Algorithm SHA256
    $Result = $MyInputHash.toUpper() -eq $Calculation.hash

    If ($Result) {
        Write-Host "The provided hash matches the calculated hash of the file." -ForegroundColor Green
        Write-Host "I: " $MyInputHash.toUpper()
        Write-Host "O: " $Calculation.hash -ForegroundColor Green
    }
    else {
        Write-Host "The provided hash does not match the calculated hash of the file." -ForegroundColor Red
        Write-Host "I: " $MyInputHash.toUpper()
        Write-Host "O: " $Calculation.hash -ForegroundColor Red
    }
}
catch [System.Management.Automation.CommandNotFoundException] {
    # Catch the error if the cmdlet is not available on the system.
    Write-Warning $Error[0]
}