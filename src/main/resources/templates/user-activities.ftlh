<#import "parts/default-set.ftlh" as c>
<@c.page user=user>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header text-center" style="background-color: #004d00; color: white;">
                        Активность пользователя
                    </div>
                    <div class="card-body">
                        <ul class="list-group">
                            <#list activities as activity>
                                <li class="list-group-item">
                                    <strong>${activity.activity}</strong>
                                    <br>
                                    <small class="text-muted">${activity.timestamp}</small>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>
