<#macro page user>
<!doctype html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>WSport</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-success" style="background-color: #117018;">
    <div class="container-fluid" style="margin: 0 15% 0 15%;">
        <a class="navbar-brand" style="color: white; font-weight: bold; font-size: 28px" href="/">
            WSport
        </a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown" style="color: white; font-size: 28px">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color: white;" >
                        Menu
                    </a>

                    <ul class="dropdown-menu" style="font-size: 20px; background-color: #117018;">
                        <li><a class="dropdown-item" href="/subscriptions">Abonement</a></li>
                        <li><a class="dropdown-item" href="/locations">Place</a></li>
                        <li><a class="dropdown-item" href="/favours">Favour</a></li>
                        <li><a class="dropdown-item" href="/about">About dev</a></li>
                        <li><a class="dropdown-item" href="/user/activities">Activity</a></li>
                    </ul>
                </li>
            </ul>

            <#if user.email??>
            <a href="/profile" style="text-decoration: none; color: white; font-weight: bold; font-size: 18px">
                Profile
            </a>
        <#else> <button class="btn btn-light my-2 my-sm-0" type="button" onclick="window.location.href = '/login'"> Войти </button>
        </#if>

        </div>
    </div>
</nav>

    <div class="container mt-4">
        <#nested>
    </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>