<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Finace App - Exclude account</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { padding-top: 70px; }
    .lang { display: none; }
    .section-title { margin-top: 40px; margin-bottom: 20px; }
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="/projeto/12">Finace App</a>
    <div class="d-flex">
      <button id="toggleLang" class="btn btn-outline-light">PT</button>
    </div>
  </div>
</nav>


<div class="container">

  <!-- ENGLISH -->
  <div id="en" class="lang">
      <h1>Account and Data Management – Finace App</h1>
      <p><em>Language: Portuguese (app not translated to English yet)</em></p>

      <h2>To delete your account:</h2>
      <ol>
          <li>Open the <strong>Finace</strong> app.</li>
          <li>Go to <strong>Configurações</strong> (Settings).</li>
          <li>Select <strong>Configurações da Conta</strong> (Account Settings).</li>
          <li>Tap <strong>Apagar Conta</strong> (Delete Account).</li>
      </ol>

      <h2>To reset your account and start fresh:</h2>
      <ol>
          <li>Open the <strong>Finace</strong> app.</li>
          <li>Go to <strong>Configurações</strong> (Settings).</li>
          <li>Select <strong>Configurações da Conta</strong> (Account Settings).</li>
          <li>Tap <strong>Reiniciar Conta</strong> (Reset Account).</li>
      </ol>

      <p>This will permanently remove your transactions, categories, and household data, allowing you to start your account from scratch.</p>
  </div>

  <!-- PORTUGUESE -->
  <div id="pt" class="lang">
      <h1>Gerenciamento de Conta e Dados – Finace App</h1>

      <h2>Para excluir sua conta:</h2>
      <ol>
          <li>Abra o aplicativo <strong>Finace</strong>.</li>
          <li>Acesse <strong>Configurações</strong>.</li>
          <li>Selecione <strong>Configurações da Conta</strong>.</li>
          <li>Toque em <strong>Apagar Conta</strong>.</li>
      </ol>

      <h2>Para reiniciar sua conta e começar do zero:</h2>
      <ol>
          <li>Abra o aplicativo <strong>Finace</strong>.</li>
          <li>Acesse <strong>Configurações</strong>.</li>
          <li>Selecione <strong>Configurações da Conta</strong>.</li>
          <li>Toque em <strong>Reiniciar Conta</strong>.</li>
      </ol>

      <p>Isso removerá permanentemente suas transações, categorias e dados da casa, permitindo começar sua conta do zero.</p>
  </div>

</div>

<script>
  const toggleBtn = document.getElementById('toggleLang');
  const enDiv = document.getElementById('en');
  const ptDiv = document.getElementById('pt');
  let currentLang = 'en';

  function showLanguage(lang) {
    if(lang === 'en') {
      enDiv.style.display = 'block';
      ptDiv.style.display = 'none';
      toggleBtn.textContent = 'PT';
    } else {
      enDiv.style.display = 'none';
      ptDiv.style.display = 'block';
      toggleBtn.textContent = 'EN';
    }
    currentLang = lang;
  }

  toggleBtn.addEventListener('click', () => {
    showLanguage(currentLang === 'en' ? 'pt' : 'en');
  });

  // Inicial: inglês
  showLanguage('en');
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
