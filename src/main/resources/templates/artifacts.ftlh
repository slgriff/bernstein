<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Deployments</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<span class="artifact">Artifact name</span> <br>
<#list deployments as deploy>
    <a href="#" class="deploy_info_link">
        <div class="deploy ${environment.status}">
            <span class="environment">${environment.name}</span> <br>
            <span class="status">${environment.status}</span> <br>
            <span class="version">${environment.version}</span> <br>
        </div>
    </a>
</#list>
</body>
</html>