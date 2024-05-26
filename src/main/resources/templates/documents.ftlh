<#import "parts/default-set.ftlh" as c>
<@c.page user>

    <h1 style="text-align: center; font-weight: bold; padding-bottom: 2%; font-size: 50px">Documents</h1>

    <div class="border border-success-subtle" style="margin: 0 7% 0 7%; padding: 3%">

        <form action="/" method="get" style="margin-bottom: 3%">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Find title" id="title" name="title" aria-label="Find title" aria-describedby="button-addon2">
                <button class="btn btn-outline-secondary" type="submit">Search</button>
            </div>
        </form>

        <table class="table table-sm">

            <tr style="text-align: center" class="align-middle">
                <th scope="col">Id</th>
                <th scope="col">Title</th>
                <th scope="col">Date create</th>
                <th scope="col">Status</th>
                <th scope="col">Assigned to</th> <!-- New column -->
                <th scope="col">Download</th>
            </tr>

            <#list documents as document>
                <#if !document.isSign()>
                    <tr style="text-align: center" class="align-middle">
                        <th scope="col">${document.id}</th>
                        <th scope="col"><a href="/document/${document.id}">${document.title}</a></th>
                        <th scope="col">${document.date_making}</th>
                        <th scope="col"><input type="submit" class="btn btn-warning mb-2" value="Wait for sign"></th>
                        <th scope="col">${document.recipients[0].user.name}</th> <!-- Accessing the assigned user -->
                        <th scope="col">
                            <form action="/document/download/${document.id}" method="get" enctype="multipart/form-data">
                                <input type="submit" class="btn btn-primary mb-2" value="Download">
                            </form>
                        </th>
                    </tr>
                </#if>

            </#list>

        </table>
    </div>
</@c.page>
