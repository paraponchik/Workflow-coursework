<#import "parts/default-set.ftlh" as c>
<@c.page user=user>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h1 class="text-center mb-4">Абонементы</h1>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Название</th>
                        <th scope="col">Место</th>
                        <th scope="col">Услуга</th>
                        <th scope="col">Общая стоимость</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list subscriptions as subscription>
                        <tr>
                            <td>${subscription.name}</td>
                            <td>${subscription.location.name}</td>
                            <td>${subscription.favour.name} - ${subscription.favour.price}</td>
                            <td>${subscription.totalPrice}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</@c.page>
