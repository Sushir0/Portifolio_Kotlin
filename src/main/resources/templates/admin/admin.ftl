<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Painel de Administração - Ciro Roberto" />
    <meta name="author" content="Ciro Roberto" />
    <title>Admin - Ciro Roberto</title>
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
            <a class="navbar-brand fw-bold" href="/admin"><h3>Admin - Ciro Roberto</h3></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="/"><h6>Dashboard</h6></a></li>
                    <li class="nav-item"><a class="nav-link text-danger" href="/logout"><h6>Sair</h6></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Admin Content -->
    <div class="container mt-5 mb-5 pt-5">
        <h2 class="text-center mb-4">Gerenciar Projetos</h2>
        <div class="text-end mb-3">
            <a href="/projeto/create" class="btn btn-success"><i class="fas fa-plus"></i> Novo Projeto</a>
        </div>
        <div class="row g-4">
            <#list projetos as projeto>
                <div class="col-md-6 col-lg-4">
                    <div class="portfolio-item">
                        <img src="${projeto.imagePath}" class="img-fluid" alt="${projeto.name}">
                        <div class="portfolio-overlay">
                            <h3>${projeto.name}</h3>
                            <p>${projeto.description}</p>
                            <div class="d-flex justify-content-between">
                                <a href="/projeto/${projeto.id}" class="btn btn-primary btn-sm"><i class="fas fa-eye"></i> Visualizar</a>
                                <a href="/projeto/${projeto.id}/editar" class="btn btn-primary btn-sm"><i class="fas fa-edit"></i> Editar</a>
                                <a href="/projeto/${projeto.id}/excluir" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Excluir</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <!-- gráficos -->
    <div class="container mb-5 mt-5">
        <div class="row">    
            <div class="col-lg-6 text-center">
                <h2 class="text-center mb-4">Estatísticas de Visitas totais</h2>
                <div class="btn-group">
                    <button class="btn btn-primary" onclick="instanciaChartTotais.update7Days()">7 Dias</button>
                    <button class="btn btn-primary" onclick="instanciaChartTotais.update30Days()">30 Dias</button>
                    <button class="btn btn-primary" onclick="instanciaChartTotais.update1Year()">1 Ano</button>
                </div>
                <div class="chart-container">
                    <canvas id="chartTotais"></canvas>
                </div>
            </div>
            <div class="col-lg-6 text-center">
                <h2 class="text-center mb-4">Estatísticas de Visitas únicas</h2>
                <div class="btn-group">
                    <button class="btn btn-primary" onclick="instanciaChartUnicas.update7Days()">7 Dias</button>
                    <button class="btn btn-primary" onclick="instanciaChartUnicas.update30Days()">30 Dias</button>
                    <button class="btn btn-primary" onclick="instanciaChartUnicas.update1Year()">1 Ano</button>
                </div>
                <div class="chart-container">
                    <canvas id="chartUnicas"></canvas>
                </div>
            </div>
        </div>
    </div>
    
    


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/static/js/utils.js"></script>
<script src="/static/js/chart.js"></script>

<script>  
    // Dados convertidos para o formato correto
    const visitLogsTotais = [
        <#list visitLogsTotais as log>
            { 
                date: "${log.accessedAt}"
            }<#if log_has_next>,</#if>
        </#list>
    ];

    const visitLogsUnicas = [
        <#list visitLogsUnicos as log>
            { 
                date: "${log.accessedAt}"
            }<#if log_has_next>,</#if>
        </#list>
    ];


    const instanciaChartTotais = new ChartFactory('chartTotais', visitLogsTotais);
    const instanciaChartUnicas = new ChartFactory('chartUnicas', visitLogsUnicas);

    instanciaChartTotais.update7Days();
    instanciaChartUnicas.update7Days();
</script>

    <style>
        .chart-container {
            position: relative;
            margin: 20px auto;
            height: 400px;
            width: 100%;
        }
        .btn-group { margin-bottom: 20px; }
    </style>





    <!-- Theme Toggle -->
    <button id="button-toggle-theme" class="btn btn-primary theme-toggle" onclick="toggleTheme()">
        <i id="icon-toggle-theme" class="fas fa-moon"></i>
    </button>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/static/js/scripts.js"></script>
</body>
</html>
