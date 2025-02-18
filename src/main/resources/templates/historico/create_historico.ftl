<!DOCTYPE html>
<html lang="pt-br">
<head>
    <!-- Meta tags mantidas da página principal -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Painel de Administração - Ciro Roberto" />
    <meta name="author" content="Ciro Roberto" />
    <title>Admin - Novo Projeto</title>
    <link rel="icon" type="image/x-icon" href="/static/images/icon.png" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@400;700&family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    
</head>
<body>
    <!-- Navigation (igual à admin existente) -->
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


    <!-- Form Container -->
    <div class="container mt-5 pt-5">
        <h2 class="text-center mb-4">Criar historico</h2>


        <#if error??>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </#if>
        
        
        <form method="post" enctype="multipart/form-data" action="/historico/create">
            <!-- Campos Básicos -->
            <div class="mb-3">
                <label class="form-label">Nome do historico</label>
                <input type="text" class="form-control" name="name" value="${(name)!}" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Texto Detalhado</label>
                <textarea class="form-control" name="text" rows="4">${(text)!}</textarea>
            </div>

            <input type="hidden" name="idProjeto" value="${idProjeto}">

            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <a href="/admin" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-success">Salvar histórico</button>
            </div>
        </form>
    </div>

    <!-- Theme Toggle -->
    <button id="button-toggle-theme" class="btn btn-primary theme-toggle" onclick="toggleTheme()">
        <i id="icon-toggle-theme" class="fas fa-moon"></i>
    </button>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
     <script src="/static/js/form.js"></script>
    <script src="/static/js/scripts.js"></script>
</body>
</html>