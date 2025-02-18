<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Login de Administração - Ciro Roberto" />
    <meta name="author" content="Ciro Roberto" />
    <title>Admin Login - Ciro Roberto</title>
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
            <a class="navbar-brand fw-bold" href="/"><h3>Ciro Roberto</h3></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="/#portfolio"><h6>Portfólio</h6></a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Login Section -->
    <section class="page-section masthead text-white min-vh-100 d-flex align-items-center">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4">
                    <div class="login-card p-4 rounded shadow">
                        <h2 class="text-center mb-4">Admin Login</h2>
                        
                        <#if error??>
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                ${error}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </#if>

                        <form method="post" action="/login" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="username" class="form-label">Usuário</label>
                                <input type="text" 
                                       class="form-control form-control-lg" 
                                       id="username" 
                                       name="username" 
                                       required
                                       placeholder="Digite seu usuário">
                                <div class="invalid-feedback">
                                    Por favor, insira seu usuário.
                                </div>
                            </div>

                            <div class="mb-4">
                                <label for="password" class="form-label">Senha</label>
                                <input type="password" 
                                       class="form-control form-control-lg" 
                                       id="password" 
                                       name="password" 
                                       required
                                       placeholder="Digite sua senha">
                                <div class="invalid-feedback">
                                    Por favor, insira sua senha.
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary btn-lg w-100">
                                <i class="fas fa-sign-in-alt me-2"></i>Entrar
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Theme Toggle -->
    <button id="button-toggle-theme" class="btn btn-primary theme-toggle" onclick="toggleTheme()">
        <i id="icon-toggle-theme" class="fas fa-moon"></i>
    </button>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/static/js/scripts.js"></script>
</body>
</html>