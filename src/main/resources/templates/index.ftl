<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Portfólio de Ciro Roberto - Desenvolvedor Android" />
    <meta name="author" content="Ciro Roberto" />
    <title>Ciro Roberto - Desenvolvedor Android</title>
    <link rel="icon" type="image/x-icon" href="/static/images/icon.png" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@400;700&family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand fw-bold " href="/"><h3>Ciro Roberto</h3></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-label="Abrir menu de navegação">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="#portfolio">Portfólio</a></li>
                    <li class="nav-item"><a class="nav-link" href="#about">Sobre</a></li>
                    <li class="nav-item"><a class="nav-link" href="#contact">Contato</a></li>
                    
                    <#if isAuthenticated>
                        <li class="nav-item"><a class="nav-link" href="/admin">Admin</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Masthead -->
    <header class="masthead text-white">
        <div class="container text-center">
            <div class="container ">
                <div class="row align-items-center">
                    <div class="col-lg-6 text-center">
                        <h1 class="mb-3">Ciro Roberto</h1>
                        <img src="static/images/imagem-perfil.jpg" class="masthead-avatar mb-4 cursor-pointer" alt="Ciro Roberto" data-bs-toggle="modal" data-bs-target="#imagemModal" tabindex="0" role="button" aria-label="Ampliar imagem do perfil">
                    </div>
                    <div class="col-lg-6">
                        <p class="lead">Formado em <span class="emphasis">Informática pelo IFSul</span>. 
                        <span class="emphasis">Desenvolvedor por paixão</span> e movido pela vontade de <span class="emphasis">criar soluções</span>. 
                        Acredito que a tecnologia mais valiosa é aquela que se <span class="emphasis">integra ao dia a dia</span>, 
                        facilitando tarefas e <span class="emphasis">resolvendo problemas reais</span>. 
                        Minha meta é desenvolver <span class="emphasis">mais do que linhas de código</span>, 
                        mas ferramentas que <span class="emphasis">gerem impacto positivo</span> e simplifiquem a vida das pessoas.
                        </p>
                    </div>
                    
                </div>
            </div>
            <div class="d-flex flex-wrap justify-content-center gap-2 mb-4" tabindex="0" aria-label="Tecnologias utilizadas">
                <#list tags as tag>
                    <span class="badge-tech badge-tech-${tag?lower_case}">${tag}</span>
                </#list>
            </div>
        </div>
    </header>

    <!-- Portfolio Section -->
    <section id="portfolio" class="page-section">
    <div class="container">
        <h2 class="text-center mb-5">Portfólio</h2>
        <div class="row g-4">
            <#list projetos as projeto>
            <div class="col-md-6 col-lg-4">
                <a href="/projeto/${projeto.id}" class="portfolio-link portfolio-item">
                    <img src="${projeto.imagePath}" class="img-fluid" alt="Imagem de ${projeto.name}">
                    <div class="portfolio-overlay">
                        <h3>${projeto.name}</h3>
                        <p>${projeto.description}</p>
                        <div class="technologies">
                            <#-- Lista até 3 tags -->
                            <#list (projeto.tags![]) as tag>
                                <#if tag?index < 3>
                                    <span class="badge bg-secondary">${tag}</span>
                                <#else>
                                    <#break> <#-- Para o loop após a 3ª tag -->
                                </#if>
                            </#list>
                            
                            <#-- Mostra "..." se houver mais de 3 tags -->
                            <#if (projeto.tags![])?size gt 3>
                                <span class="badge bg-dark">...</span>
                            </#if>
                        </div>
                    </div>
                </a>
            </div>
            </#list>
        </div>
    </div>
</section>

    <!-- About Section -->
    <section id="about" class="page-section about-section" tabindex="0">
        <div class="container">
            <h2 class="text-center mb-5" >Sobre Mim</h2>
            <div class="row">
    <div class="col-lg-6">
        <p class="lead">
            Nasci em São Jerônimo, no Rio Grande do Sul. 
            Atualmente, dedico meu tempo a projetos pessoais como desenvolvedor de aplicativos para Android e backend, 
            sempre em busca de evolução e de adquirir mais experiência na área.
            <br>
            Estudei no IFSul, onde fiz um curso técnico integrado ao ensino médio em Informática. 
            Foi nesse ambiente que fui inserido no mundo da tecnologia, 
            começando a aprender e me interessar por programação e desenvolvimento de software, o que motivou meus projetos pessoais na área.
        </p>
    </div>
    <div class="col-lg-6">
        <p class="lead">
            Além da tecnologia, tenho paixão por fotografia de paisagens, 
            explorar novos lugares e aproveitar os pequenos momentos da vida. 
            Também gosto de assistir a bons filmes ou simplesmente dar uma caminhada para relaxar.
            <br>
            Acredito que a vida é um equilíbrio entre trabalho e lazer, desafios e momentos de descanso. 
            E no fim das contas, estou sempre em busca  de apreciar as pequenas coisas do cotidiano.
        </p>
    </div>
</div>
        </div>
    </section>

    <!-- Statistics Section -->
    <section class="page-section">
        <div class="container">
            <h2 class="text-center mb-5">Estatísticas do Site</h2>
            <div class="row justify-content-center g-4"  tabindex="0" aria-label="Estatísticas do site">
                <!-- Unique Visitors Card -->
                <div class="col-md-6 col-lg-4">
                    <div class="stat-card text-center p-4 h-100" tabindex="0">
                        <div class="stat-icon mb-3">
                            <i class="fas fa-users fa-3x"></i>
                        </div>
                        <h3 class="stat-number mb-2">${visitantesUnicos}</h3>
                        <p class="stat-label mb-0">Visitantes Únicos</p>
                    </div>
                </div>

                <!-- Total Visits Card -->
                <div class="col-md-6 col-lg-4">
                    <div class="stat-card text-center p-4 h-100" tabindex="0">
                        <div class="stat-icon mb-3">
                            <i class="fas fa-eye fa-3x"></i>
                        </div>
                        <h3 class="stat-number mb-2">${visitasTotais}</h3>
                        <p class="stat-label mb-0">Visitas Totais</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact" class="page-section">
        <div class="container">
            <div class="contact-card" tabindex="0" aria-label="Contato de Ciro Roberto">
                <h2 class="text-center mb-4">Vamos Conectar!</h2>
                <div class="d-flex flex-wrap justify-content-center gap-3">
                    <a href="https://linkedin.com/in/ciro-roberto" class="btn btn-outline-light btn-lg" aria-label="LinkedIn de Ciro Roberto">
                        <i class="fab fa-linkedin"></i> LinkedIn
                    </a>
                    <a href="https://github.com/Sushir0" class="btn btn-outline-light btn-lg" aria-label="GitHub de Ciro Roberto">
                        <i class="fab fa-github"></i> GitHub
                    </a>
                    <a href="https://api.whatsapp.com/send?phone=5551981678088&text=ol%C3%A1%2C%20estou%20interessado" class="btn btn-outline-light btn-lg" aria-label="Enviar mensagem no WhatsApp para Ciro Roberto">
                        <i class="fab fa-whatsapp"></i> Whatsapp
                    </a>
                </div>
            </div>
        </div>
    </section>



    <div class="modal fade" id="imagemModal" tabindex="-1" aria-labelledby="imagemModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content bg-transparent border-0">
            <div class="modal-header border-0">
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body text-center">
                <img src="static/images/imagem-perfil.jpg" class="img-fluid rounded" alt="Ciro Roberto em tamanho maior">
            </div>
            </div>
        </div>
    </div>




    <!-- Theme Toggle -->
    <button id="button-toggle-theme" class="btn btn-primary theme-toggle" onclick="toggleTheme()" aria-label="Alternar tema claro e escuro">
        <i id="icon-toggle-theme" class="fas fa-moon"></i>
    </button>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/scripts.js"></script>
</body>
</html>