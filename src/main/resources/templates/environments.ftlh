<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Deployments</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <span class="asdf">Environments Deploy List</span>
    <table>
        <#list environments as environment>
            <tr>
                <th>${environment.name}</th>
            </tr>
            <#list deployments as deployment>
                <tr>
                    <td>${deployment.name}</td>
                    <td>${deployment.version}</td>
                </tr>
            </#list>
        </#list>
    </table>
</body>
</html>