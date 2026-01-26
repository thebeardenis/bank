function loadData() {
    fetch('/api/user/get_all_users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            client: 'browser',
            timestamp: new Date().toISOString()
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка HTTP: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        const container = document.getElementById('all-users-data');
        for (var i=0; i<data.length; i++) {
            var user = data[i];
            container.innerHTML += `
                <div>
                <p><strong>Name:</strong> ${user.name}</p>
                <p><strong>Email:</strong> ${user.email}</p>
                <a href="http://localhost:8080/view_cards/${user.email}">View user cards</a>
                </div>
            `
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-users-data').innerHTML =
            `<p style="color: red;">Ошибка при загрузке данных: ${error.message}</p>`;
    });
}