<#import "parts/default-set.ftlh" as c>
<@c.page user>

    <h1 style="text-align: center; font-weight: bold; padding-bottom: 2%; font-size: 50px">Admin Panel</h1>

    <div class="d-flex justify-content-center mb-4">
        <a href="/admin" class="btn btn-outline-success me-2">Users</a>
        <a href="/admin/favours" class="btn btn-outline-success me-2">Favours</a>
        <a href="/admin/places" class="btn btn-outline-success">Places</a>
    </div>

    <div class="border border-success-subtle" style="margin: 0 7% 0 7%; padding: 3%">
        <table class="table table-sm align-middle" style="text-align: center">
            <tr>
                <th scope="col">Email</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Active</th>
                <th scope="col">Role</th>
                <th scope="col">Ban</th>
                <th scope="col">Change role</th>
                <th scope="col">More details</th>
            </tr>

            <#list users as user>
                <tr>
                    <th scope="col">${user.email}</th>
                    <th scope="col">${user.name}</th>
                    <th scope="col">${user.surname}</th>
                    <th scope="col"><#if user.active>true<#else>false</#if></th>
                    <th scope="col"><#list user.roles as role> ${role}</#list></th>
                    <th scope="col">
                        <form action="/admin/user/ban/${user.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <#if user.active>
                                <input type="submit" value="Ban" class="btn btn-danger mb-2">
                            <#else>
                                <input type="submit" value="Unban" class="btn btn-primary mb-2">
                            </#if>
                        </form>
                    </th>
                    <th scope="col">
                        <a href="/admin/user/edit/${user.id}">
                            <button type="button" class="btn btn-success">
                                Edit
                            </button>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="/user/${user.id}">
                            <button type="button" class="btn btn-secondary">
                                Detailed
                            </button>
                        </a>
                    </th>
                </tr>
            <#else>
                <h3>Users not found</h3>
            </#list>
        </table>
    </div>
</@c.page>
