
function toggleMessages() {
    const message1 = document.getElementById('message1');
    const message2 = document.getElementById('message2');
    
    if (message1.classList.contains('active')) {
        message1.classList.remove('active');
        message2.classList.add('active');
    } else {
        message2.classList.remove('active');
        message1.classList.add('active');
    }
}


document.addEventListener('DOMContentLoaded', function() {
    setInterval(toggleMessages, 3000);
}); 