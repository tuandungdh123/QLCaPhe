document.getElementById('user-menu-button').addEventListener('click', function() {
    var dropdown = document.getElementById('dropdown');
    if (dropdown.classList.contains('hidden')) {
        dropdown.classList.remove('hidden');
    } else {
        dropdown.classList.add('hidden');
    }
});

// Optional: Close dropdown when clicking outside
window.addEventListener('click', function(event) {
    var dropdown = document.getElementById('dropdown');
    var button = document.getElementById('user-menu-button');
    if (!button.contains(event.target) && !dropdown.contains(event.target)) {
        dropdown.classList.add('hidden');
    }
});