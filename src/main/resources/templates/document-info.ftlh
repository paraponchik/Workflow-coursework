<#import "parts/default-set.ftlh" as c>
<@c.page user>

    <h1 style="text-align: center;">Full info about document: ${document.title}</h1>

    <div class="border border-success-subtle" style="text-align: center; margin: 0 15% 0 15%; padding: 2%">

        <div class="bg-success p-2 text-white" style="margin: 1% 30% 2% 30%; border-radius: 15px">
            <b>Описание: </b> ${document.description}<br>
            <b>id:</b> ${document.id}<br>
            <b>Date create:</b> ${document.date_making}<br>
            <b>Creator: </b><a href="/user/${document.user.id}" style="text-decoration: none; color: yellow">${document.user.name} ${document.user.surname}</a><br>
        </div>

        <div class="col-md-3 d-flex justify-content-center" style="margin-left: 37%">
            <#if document.isSign()>
                <#else>
                    <form action="/document/sign/${document.id}" method="post" style="margin-right: 4%">
                        <input type="submit" class="btn btn-success" value="Sign" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                    </form>
            </#if>

                <form action="/document/delete/${document.id}" method="post" style="margin-right: 4%">
                    <input type="submit" class="btn btn-danger" value="Delete" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>

                <form action="/document/download/${document.id}" method="get" style="margin-right: 4%">
                    <input type="submit" class="btn btn-primary" value="Download">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>

        </div>
    </div>

</@c.page>