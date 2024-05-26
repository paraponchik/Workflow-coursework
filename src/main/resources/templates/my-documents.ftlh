<#import "parts/default-set.ftlh" as c>
<@c.page user>
    <h1 style="text-align: center">My documents</h1>
    <hr>

    <a class="btn btn-success mt-3 mb-3" style="text-align: center; width: 20%; margin: 0 7% 0 7%;"
       data-toggle="collapse" href="/document/create/new" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Create new document
    </a>

        <div class="border border-success-subtle" style="margin: 0 7% 0 7%; padding: 3%;">
            <#list documents as document>
                <#if !document.isSign()>
                    <a href="/document/${document.id}" class="document-link" style=" text-decoration: none">
                        <div class="card text-white bg-secondary my-3 document">
                            <div class="card-top" style="margin: 1% 0 0 1%">
                                <form action="/document/delete/${document.id}" method="post">
                                    <input type="submit" class="btn btn-danger" value="Delete" />
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                </form>
                            </div>

                            <div class="card-body">
                                <h5 class="card-title">${document.title}</h5>
                                ${document.date_making}
                            </div>
                        </div>
                    </a>
                    <#else>

                        <a href="/document/${document.id}" class="document-link" style=" text-decoration: none">
                            <div class="card text-white bg-info my-3 document">
                                <div class="card-body">
                                    <h4 class="card-title" style="color: yellow">Document is signed</h4>
                                    <h5 class="card-title">${document.title}</h5>
                                    ${document.date_making}
                                </div>
                            </div>
                        </a>

                </#if>
            </#list>
    </div>
</@c.page>