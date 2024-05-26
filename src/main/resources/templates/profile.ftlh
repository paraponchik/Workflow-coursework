<#import "parts/default-set.ftlh" as p>
<@p.page user>

    <h1 style="text-align: center">Personal data</h1>
    <br>

    <div class="border border-success-subtle" style="text-align: center; margin: 0 15% 0 15%; padding: 2%">

        <h2 style="font-weight: bold; font-size: 50px">${user.name}</h2>

        <div class="bg-success p-2 text-white" style="margin: 1% 30% 2% 30%; border-radius: 15px">
            <p><b>E-mail:</b> ${user.email}</p>
            <p><b>Name:</b> ${user.name}</p>
            <p><b>Surname:</b> ${user.surname}</p>
            <p><b>Position:</b> ${user.position}</p>
        </div>


        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 40%"
               href="/admin">
                Admin panel
            </a><br>
        </#if>

        <a class="btn btn-success mb-3" style="text-align: center; width: 40%"
           href="/my/documents">
            My documents
        </a>

        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 40%" class="btn btn-danger mb-2">
                Log out
            </button>
        </form>

    </div>
</@p.page>