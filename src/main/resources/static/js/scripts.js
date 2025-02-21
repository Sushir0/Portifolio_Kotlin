// Função para alternar o tema
function toggleTheme() {
    document.body.classList.toggle('dark-mode');
    if (document.body.classList.contains('dark-mode')) {
        localStorage.setItem('theme', 'dark');
    } else {
        localStorage.setItem('theme', 'light');
    }
    applySavedTheme()
}

function applyTheme(theme) {
    const btn = document.getElementById('button-toggle-theme');
    const icon = document.getElementById('icon-toggle-theme')
    
    if (theme === 'dark') {
        document.body.classList.add('dark-mode');
        icon.classList.replace('fa-moon', 'fa-sun');
        btn.classList.replace('btn-primary', 'btn-light');
    } else if (theme === 'light') {
        document.body.classList.remove('dark-mode');
        icon.classList.replace('fa-sun', 'fa-moon');
        btn.classList.replace('btn-light', 'btn-primary');
    }
}

function applySavedTheme() {
    const savedTheme = localStorage.getItem('theme');

    if (savedTheme === 'dark' || savedTheme === 'light') {
        applyTheme(savedTheme);
    } else {
        // Verifica o tema do sistema operacional
        const isDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
        const isLightMode = window.matchMedia('(prefers-color-scheme: light)').matches;
        if (isDarkMode) {
            applyTheme('dark'); 
        } else if (isLightMode) {
            applyTheme('light'); 
        } else {
            applyTheme('light');
        }
    }
}

// Ativa animações ao scroll
window.addEventListener('scroll', () => {
    document.querySelectorAll('.fade-in').forEach(el => {
        const rect = el.getBoundingClientRect();
        if(rect.top < window.innerHeight - 100) {
            el.classList.add('visible');
        }
    });
});

// Controle de toque para mobile
document.querySelectorAll('.portfolio-link').forEach(link => {
    let touchStartTime;
    
    link.addEventListener('touchstart', (e) => {
        touchStartTime = new Date().getTime();
    }, { passive: true });
    
    link.addEventListener('touchend', (e) => {
        const touchDuration = new Date().getTime() - touchStartTime;
        
        // Toque rápido (menos de 300ms) segue o link normalmente
        if (touchDuration < 300) return;
        
        // Toque longo mantém overlay visível
        e.preventDefault();
        const overlay = link.querySelector('.portfolio-overlay');
        overlay.style.opacity = overlay.style.opacity === '1' ? '0' : '1';
    }, { passive: false });
});

// Adicione esta parte para melhorar a acessibilidade
document.querySelectorAll('.portfolio-link').forEach(link => {
    link.addEventListener('click', (e) => {
        // Garante que o clique via teclado também funcione
        if (e.detail === 0) { // Clique com teclado
            window.location.href = link.href;
        }
    });
});

document.addEventListener('DOMContentLoaded', () => {
    setTimeout(() => applySavedTheme(), 10);

});

// Controles do carrossel
document.querySelectorAll('.history-control').forEach(control => {
    control.addEventListener('click', () => {
        const container = document.querySelector('.history-items');
        const itemWidth = document.querySelector('.history-item').offsetWidth + 20;
        container.scrollBy({
            left: control.classList.contains('next') ? itemWidth : -itemWidth,
            behavior: 'smooth'
        });
    });
});

document.addEventListener('DOMContentLoaded', () => {
    const carousel = document.querySelector('.history-carousel');
    const itemsContainer = carousel.querySelector('.history-items');
    const prevBtn = carousel.querySelector('.prev');
    const nextBtn = carousel.querySelector('.next');

    function updateControls() {
        // Verifica se há overflow
        const hasOverflow = itemsContainer.scrollWidth > itemsContainer.clientWidth;
        
        // Atualiza botões baseado no scroll e overflow
        prevBtn.classList.toggle('hidden', !hasOverflow || itemsContainer.scrollLeft <= 0);
        nextBtn.classList.toggle('hidden', !hasOverflow || itemsContainer.scrollLeft + itemsContainer.clientWidth >= itemsContainer.scrollWidth);
    }

    // Controles de scroll
    prevBtn.addEventListener('click', () => {
        itemsContainer.scrollBy({ left: -itemsContainer.clientWidth, behavior: 'smooth' });
    });

    nextBtn.addEventListener('click', () => {
        itemsContainer.scrollBy({ left: itemsContainer.clientWidth, behavior: 'smooth' });
    });

    // Atualiza ao interagir ou redimensionar
    itemsContainer.addEventListener('scroll', updateControls);
    window.addEventListener('resize', updateControls);
    
    // Verificação inicial
    updateControls();
});