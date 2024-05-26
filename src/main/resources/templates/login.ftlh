<#import "parts/default-set.ftlh" as c>
<@c.page user>
    <div class="border border-success-subtle" style="margin: 10% 20% 10% 20%; padding: 5%">
        <h1 style="text-align: center; font-weight: bold">Authorisation</h1>
        <form action="/login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="email" name="username">
                <div id="email" class="invalid-feedback">
                    Incorrect email!
                </div>
            </div>
            <br>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
                <div id="password" class="invalid-feedback">
                    Incorrect password!
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <br>
            <button type="submit" style="width: 100%; background-color: #117018; font-weight: bold; color: white; font-size: 16px; margin-bottom: 1%" class="btn">log in</button>
        </form>
        <h6>You haven't accounted? <a href="/registration">Registration!</a></h6>
    </div>
</@c.page>