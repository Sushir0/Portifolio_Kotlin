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
      <h1>Account Deletion Information</h1>
      <p><em>Language: Portuguese (app not translated to English yet)</em></p>
      <p>To delete your account in the <strong>Finace</strong> app, follow the steps below:</p>
      <ol>
          <li>Open the Finace app.</li>
          <li>Go to the <strong>Configurações</strong> menu.</li>
          <li>Select the <strong>Excluir Conta</strong> option.</li>
          <li>Confirm the deletion.</li>
      </ol>
      <p>If you prefer, send an email to <a href="mailto:finace.projeto@gmail.com">finace.projeto@gmail.com</a> requesting the deletion.</p>
  </div>

  <!-- PORTUGUESE -->
  <div id="pt" class="lang">
    <h1>Excluir Conta</h1>
    <p>Para excluir sua conta no aplicativo <strong>Finance</strong>, siga os passos abaixo:</p>
    <ol>
        <li>Abra o aplicativo Finance.</li>
        <li>Acesse o menu <strong>Configurações</strong>.</li>
        <li>Selecione a opção <strong>Excluir Conta</strong>.</li>
        <li>Confirme a exclusão.</li>
    </ol>
    <p>Se preferir, envie um e-mail para <a href="mailto:suporte@finance.com">suporte@finance.com</a> solicitando a exclusão.</p>
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
