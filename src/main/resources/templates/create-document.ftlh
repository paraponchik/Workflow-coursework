<#import "parts/default-set.ftlh" as c>
<@c.page user>
<div class="container">
    <h1 class="mt-4">Create Document</h1>
    <form action="/document/create" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <div class="mb-3">
            <label for="file" class="form-label">File</label>
            <input type="file" class="form-control" id="file" name="file" required>
        </div>
        <div class="mb-3">
            <label for="userId" class="form-label">Assign to User</label>
            <select class="form-select" id="userId" name="userId" required>
                <#list users as user>
                    <option value="${user.id}">${user.name} (${user.email})</option>
                </#list>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>
</@c.page>
