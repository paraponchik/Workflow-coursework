<#import "parts/default-set.ftlh" as c>
<@c.page user=user>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h1 class="text-center mb-4">Услуги</h1>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Название</th>
                        <th scope="col">Цена</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list favours as favour>
                        <tr>
                            <td>${favour.id}</td>
                            <td>${favour.name}</td>
                            <td>${favour.price}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</@c.page>
