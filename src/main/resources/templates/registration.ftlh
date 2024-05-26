<#import "parts/default-set.ftlh" as c>
<@c.page user>
    <div class="border border-success-subtle" style="margin: 10% 20% 10% 20%; padding: 5%">

        <h1 style="text-align: center; font-weight: bold">Registration</h1>

        <form action="/registration" method="post">

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" required name="email">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" required name="password">
            </div>

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" required name="name">
            </div>

            <div class="form-group">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" id="surname" required name="surname">
            </div>

            <div class="form-group">
                <label for="position">Position</label>
                <input type="text" class="form-control" id="position" required name="position">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <br>
            <button type="submit" style="width: 100%; background-color: #117018; font-weight: bold; color: white; font-size: 16px; margin-bottom: 1%" class="btn">Sign up</button>
        </form>
    </div>
</@c.page>
