<#import "parts/default-set.ftlh" as c>
<@c.page user=user>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <h1 class="text-center mb-4">Создать абонемент</h1>
                <form action="/subscription/create" method="post">
                    <div class="form-group mb-3">
                        <label for="name">Название:</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="location">Место:</label>
                        <select id="location" name="locationId" class="form-control" required>
                            <#list locations as location>
                                <option value="${location.id}">${location.name} - ${location.price}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="favours">Услуги:</label>
                        <#list favours as favour>
                            <div class="form-check">
                                <input type="checkbox" id="favour${favour.id}" name="favourIds" value="${favour.id}" class="form-check-input">
                                <label class="form-check-label" for="favour${favour.id}">${favour.name} - ${favour.price}</label>
                            </div>
                        </#list>
                    </div>
                    <button type="submit" class="btn btn-primary">Создать</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</@c.page>
