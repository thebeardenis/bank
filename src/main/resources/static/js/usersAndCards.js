function loadDataOfAllUsers() {
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
                <div style="border: 2px solid #000; border-radius: 4px; background-color: rgba(255, 255, 255, 0.5); padding: 5px; margin: 5px;">
                <p  style="margin: 3px;"><strong>Name:</strong> ${user.name}</p>
                <p  style="margin: 3px;"><strong>Email:</strong> ${user.email}</p>
                <a href="http://localhost:8080/view_cards/${user.id}">View user cards</a><br>
                <a href="http://localhost:8080/view_transactions/${user.id}">View user transactions</a>
                </div>
            `
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-users-data').innerHTML =
            `<p style="color: red;">Error: ${error.message}</p>`;
    });
}


function loadDataCardsByUserId(id) {
    fetch('/api/card/get_user_cards_by_id/' + id, {
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
        const container = document.getElementById('all-cards-data');
        for (var i=0; i<data.length; i++) {
            var card = data[i];
            container.innerHTML += `
                <div style="border: 2px solid #000; border-radius: 4px; background-color: rgba(255, 255, 255, 0.5); padding: 5px;">
                <p><strong>Name:</strong> ${card.name}</p>
                <p><strong>Balance:</strong> ${card.balance}</p>
                </div>
            `
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-cards-data').innerHTML =
            `<p style="color: red;">Error: ${error.message}</p>`;
    });
}