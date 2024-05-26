<#import "parts/default-set.ftlh" as c>
<@c.page user>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h1 class="text-center mb-4">Редактирование пользователя ${editUser.name}</h1>
                <form action="/admin/user/edit" method="post">
                    <div class="form-group mb-3">
                        <label for="name">Имя:</label>
                        <input type="text" id="name" name="name" class="form-control" value="${editUser.name}">
                    </div>
                    <div class="form-group mb-3">
                        <label for="surname">Фамилия:</label>
                        <input type="text" id="surname" name="surname" class="form-control" value="${editUser.surname}">
                    </div>
                    <div class="form-group mb-3">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" class="form-control" value="${editUser.email}">
                    </div>
                    <div class="form-group form-check mb-3">
                        <input type="checkbox" id="active" name="active" class="form-check-input" ${editUser.active?string("checked", "")}>
                        <label for="active" class="form-check-label">Активен</label>
                    </div>
                    <div class="mb-3">
                        <label>Роль:</label>
                        <#list roles as role>
                            <div class="form-check">
                                <input type="radio" name="role" id="role-${role}" value="${role}" class="form-check-input" ${editUser.roles?seq_contains(role)?string("checked", "")}>
                                <label class="form-check-label" for="role-${role}">${role}</label>
                            </div>
                        </#list>
                    </div>
                    <input type="hidden" value="${editUser.id}" name="userId">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-success w-100">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>
