<!DOCTYPE html>
<html lang="pt-br">
<head>
    <!-- Meta tags mantidas da página principal -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="${projeto.name} - Projeto desenvolvido por Ciro Roberto" />
    <meta name="author" content="Ciro Roberto" />
    <title>${projeto.name} - Ciro Roberto</title>
    <link rel="icon" type="image/x-icon" href="/static/images/icon.png" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@400;700&family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <!-- Navigation (igual à principal) -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand fw-bold " href="/"><h3>Ciro Roberto</h3></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="/#portfolio">Portfólio</a></li>
                    <li class="nav-item"><a class="nav-link" href="/#about">Sobre</a></li>
                    <li class="nav-item"><a class="nav-link" href="/#contact">Contato</a></li>
                    
                    <#if isAuthenticated>
                        <li class="nav-item"><a class="nav-link" href="/admin">Admin</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Project Header -->
    <header class="project-header text-white">
        <div class="container text-center mb-5">
            <div class="project-header-content">
                <img src="${projeto.imagePath}" class="project-main-image cursor-pointer" alt="${projeto.name}" tabindex="0" role="button" aria-label="Ampliar imagem do projeto ${projeto.name}" data-bs-toggle="modal" data-bs-target="#imagem_projeto">
                <h1 class="mt-4 mb-3">${projeto.name}</h1>
                <div class="project-metadata">
                    <div class="metadata-item">
                        <i class="fas fa-calendar-alt"></i>
                        <span>Criado em: ${projeto.createdAt?string['dd/MM/yyyy']}</span>
                    </div>
                    <div class="metadata-item">
                        <i class="fas fa-sync-alt"></i>
                        <span>Última atualização: ${projeto.updatedAt?string['dd/MM/yyyy']}</span>
                    </div>
                </div>
                <#if projeto.gitHubUrl?has_content>
                    <a href="${projeto.gitHubUrl}" target="_blank" class="btn btn-primary btn-github" aria-label="Ver projeto no GitHub">
                        <i class="fab fa-github"></i> Ver no GitHub
                    </a>
                </#if>
                <#if projeto.downloadUrl?has_content>
                    <a href="${projeto.downloadUrl}" target="_blank" class="btn btn-primary btn-github" aria-label="Baixar projeto">
                        <i class="fab fa-google-play"></i> Baixar Aplicativo
                    </a>
                </#if>
                <div class="project-tags mt-3 mb-4" aria-label="Tecnologias utilizadas no projeto" tabindex="0">
                    <#list projeto.tags as tag>
                        <span class="badge-tech badge-tech-${tag?lower_case}">${tag}</span>
                    </#list>
                </div>
            </div>
        </div>
    </header>

    <!-- Project Content -->
    <main class="project-container">
        <section class="project-description" tabindex="0">
            <div class="container">
                <h2 class="section-title">Descrição do Projeto</h2>
                <div class="content-box">
                    <div class="detailed-text">${projeto.text}</div>
                </div>
            </div>
        </section>

       <section class="project-features">
            <div class="container">
                <h2 class="section-title mb-5">Funcionalidades Principais</h2>
                <#if isAuthenticated>
                    <div class="text-end mb-3">
                        <a href="/projeto/${projeto.id}/feature/create" class="btn btn-success"><i class="fas fa-plus"></i> Nova feature</a>
                    </div>
                </#if>
                
                <div id="featuresCarousel" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <#list projeto.features as feature>
                        <div class="carousel-item <#if feature?is_first>active</#if>">
                            <div class="row align-items-center">
                                <!-- Coluna da Imagem com tamanho fixo -->
                                <div class="col-md-6 image-container">
                                    <div class="fixed-image-wrapper">
                                        <img src="${feature.imagePath}" 
                                            class="d-block w-100 h-100 cursor-pointer" 
                                            alt="Imagem de ${feature.name}"
                                            data-bs-toggle="modal" 
                                            data-bs-target="#imagem_feature_${feature.id}">
                                    </div>
                                </div>
                                
                                <!-- Coluna do Texto -->
                                <div class="col-md-6">
                                    <div class="feature-text-content ps-md-4">
                                        <h3 class="feature-title mb-3">${feature.name}</h3>
                                        <div class="feature-text mb-4">${feature.text}</div>
                                        <div class="update-date">
                                            <small>
                                                Criado em: ${feature.createdAt?string['dd/MM/yyyy']}
                                                <#if isAuthenticated>
                                                    <a href="/feature/${feature.id}/editar" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i> Editar</a>
                                                    <a href="/feature/${feature.id}/excluir" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Excluir</a>

                                                </#if>
                                            </small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </#list>
                    </div>
                    
                    <!-- Controles do Carrossel -->
                    <#if projeto.features?size gte 2>
                    <button class="carousel-control-prev" type="button" data-bs-target="#featuresCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Anterior</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#featuresCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Próximo</span>
                    </button>
                    </#if>
                </div>
            </div>
        </section>

        <section class="project-history">
    <div class="container">
        <h2 class="section-title">Linha do Tempo</h2>
        <#if isAuthenticated>
            <div class="text-end mb-3">
                <a href="/projeto/${projeto.id}/historico/create" class="btn btn-success"><i class="fas fa-plus"></i> Novo histórico</a>
            </div>
        </#if>
        
        <div class="history-carousel">
            <div class="history-items">
                <#list projeto.historicos as log>
                    <div class="history-item">
                        <div class="update-date">${log.createdAt?string['dd/MM/yyyy']}</div>
                        <h4 class="history-title">${log.name}</h4>
                        <p class="history-text">${log.text}</p>
                        <#if isAuthenticated>
                            <a href="/historico/${log.id}/editar" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i> Editar</a>
                            <a href="/historico/${log.id}/excluir" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Excluir</a>

                        </#if>
                    </div>
                </#list>
            </div>
            <button class="history-control prev"><i class="fas fa-chevron-left"></i></button>
            <button class="history-control next"><i class="fas fa-chevron-right"></i></button>
        </div>
    </div>
</section>
    </main>

    <div class="modal fade" id="imagem_projeto" tabindex="-1" aria-labelledby="imagemModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content bg-transparent border-0">
                <div class="modal-header border-0">
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Fechar"></button>
                </div>
                <div class="modal-body text-center">
                    <img src="${projeto.imagePath}" class="img-fluid rounded" alt="imagem do ${projeto.name}">
                </div>
            </div>
        </div>
    </div>

    <#list projeto.features as feature>
        <div class="modal fade" id="imagem_feature_${feature.id}" tabindex="-1" aria-labelledby="imagemModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content bg-transparent border-0">
                    <div class="modal-header border-0">
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Fechar"></button>
                    </div>
                    <div class="modal-body text-center">
                        <img src="${feature.imagePath}" class="img-fluid rounded" alt="imagem do ${feature.name}">
                    </div>
                </div>
            </div>
        </div>
    </#list>

    <!-- Theme Toggle e scripts (mantidos da página principal) -->
    <button id="button-toggle-theme" class="btn btn-primary theme-toggle" onclick="toggleTheme()">
        <i id="icon-toggle-theme" class="fas fa-moon"></i>
    </button>




    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/static/js/scripts.js"></script>
</body>
</html>