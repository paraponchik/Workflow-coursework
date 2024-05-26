<#import "parts/default-set.ftlh" as c>
<@c.page user>

    <h1 style="text-align: center; font-weight: bold; padding-bottom: 2%; font-size: 50px">Edit Favour</h1>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <form action="/admin/favour/edit" method="post">
                    <input type="hidden" name="id" value="${favour.id}">
                    <div class="form-group mb-3">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" class="form-control" value="${favour.name}" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="price">Price:</label>
                        <input type="number" id="price" name="price" class="form-control" value="${favour.price}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Favour</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</@c.page>
