<#import "parts/default-set.ftlh" as c>
<@c.page user>

    <h1 style="text-align: center; font-weight: bold; padding-bottom: 2%; font-size: 50px">Manage Favours</h1>

    <div class="d-flex justify-content-center mb-4">
        <a href="/admin" class="btn btn-outline-success me-2">Users</a>
        <a href="/admin/favours" class="btn btn-outline-success me-2">Favours</a>
        <a href="/admin/places" class="btn btn-outline-success">Places</a>
    </div>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h2 class="text-center mb-4">Add New Favour</h2>
                <form action="/admin/favour/create" method="post">
                    <div class="form-group mb-3">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="price">Price:</label>
                        <input type="number" id="price" name="price" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success">Add Favour</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>
                <hr>
                <h2 class="text-center mb-4">Existing Favour</h2>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list favours as favour>
                        <tr>
                            <td>${favour.name}</td>
                            <td>${favour.price}</td>
                            <td>
                                <a href="/admin/favour/edit/${favour.id}" class="btn btn-success">Edit</a>
                            </td>
                            <td>
                                <form action="/admin/favour/delete/${favour.id}" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</@c.page>
