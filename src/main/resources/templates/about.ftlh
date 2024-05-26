<#import "parts/default-set.ftlh" as c>
<@c.page user=user>
    <style>
        .about-card {
            background-color: #f8f9fa;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }

        .about-card:hover {
            transform: scale(1.05);
        }

        .profile-img {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border: 5px solid #004d00;
            transition: border-color 0.3s;
        }

        .profile-img:hover {
            border-color: #003300;
        }

        .card-header {
            background-color: #004d00;
            color: white;
        }

        .card-title {
            font-family: 'Arial', sans-serif;
            font-size: 2em;
            margin-bottom: 0.5em;
            color: #004d00;
            animation: fadeInDown 1s;
        }

        .card-text {
            font-family: 'Verdana', sans-serif;
            color: #333;
            margin: 15px 0;
            animation: fadeInUp 1s;
        }

        .divider {
            height: 2px;
            background-color: #004d00;
            margin: 20px 0;
        }

        .github-link {
            display: inline-block;
            padding: 10px 20px;
            background-color: #004d00;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
        }

        .github-link:hover {
            background-color: #003300;
        }

        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card about-card">
                    <div class="card-header text-center">
                        Обо мне
                    </div>
                    <div class="card-body text-center">
                        <img src="/img/bugs.jpg" alt="Profile Image" class="profile-img rounded-circle mb-4">
                        <h2 class="card-title">Готовчиц Антон</h2>
                        <p class="card-text">Студент группы 181073, ИИТ БГУИР</p>
                        <div class="divider"></div>
                        <p class="card-text">Привет! Меня зовут Антон Готовчиц, и я студент в Институте Информационных Технологий Белорусского Государственного Университета Информатики и Радиоэлектроники (БГУИР). Моя группа - 181073.</p>
                        <div class="divider"></div>
                        <p class="card-text">Я увлечен изучением новых технологий и программирования. В свободное время люблю заниматься спортом и читать книги.</p>
                        <div class="divider"></div>
                        <a href="https://github.com/paraponchik" class="github-link" target="_blank">Мой GitHub</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>
