<#import "parts/default-set.ftlh" as c>
<@c.page user>

    <div class="border border-success-subtle" style="text-align: center; margin: 0 10% 0 10%; padding: 2% 0 2% 0">
        <h1>Info about: ${user.name}</h1><br>
        <div class="bg-success p-2 text-white" style="text-align: left; margin: 1% 8% 2% 8%; border-radius: 15px">
            <h2>Name: ${user.name}</h2><br>
            <h2>Surname: ${user.surname}</h2><br>
            <h2>Email: ${user.email}</h2><br>
            <h2>Position: ${user.position}</h2><br>
        </div>
        <h1>Documents assigned to ${user.name}</h1><br>

        <div class="bg-info text-dark p-2 text-white" style="text-align: left; margin: 1% 8% 2% 8%; border-radius: 5px">
            <#list documents as document>

                <table class="table table-sm align-middle">
                    <tr style="text-align: center">
                        <th scope="col">${document.id}</th>
                        <th scope="col"><a href="/document/${document.id}">${document.title}</a></th>
                        <th scope="col">${document.date_making}</th>
                        <#if document.isSign()>
                            <th scope="col"><input type="button" class="btn btn-success mb-2" value="Signed"></th>
                        <#else> <th scope="col"><input type="button" class="btn btn-warning mb-2" value="Waiting"></th>
                        </#if>
                        <#if document.recipients?has_content && document.recipients[0].assignedBy?exists>
                            <th scope="col">Assigned by: ${document.recipients[0].assignedBy.name} ${document.recipients[0].assignedBy.surname}</th>
                        <#else>
                            <th scope="col">Assigned by: Unknown</th>
                        </#if>

                        <th scope="col">
                            <form action="/document/download/${document.id}" method="get" enctype="multipart/form-data">
                                <input type="submit" class="btn btn-primary mb-2" value="Download">
                            </form>
                        </th>
                    </tr>
                </table>

            <#else>
                <h3>This user has not been assigned any documents yet.</h3>
            </#list>
        </div>

    </div>

    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>

</@c.page>
