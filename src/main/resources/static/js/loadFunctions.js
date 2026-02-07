function transaction(idFrom, idTo, amount) {
    const formData = new URLSearchParams();
    formData.append('idFrom', idFrom);
    formData.append('idTo', idTo);
    formData.append('amount', amount);

    fetch('/api/transaction/go_transaction', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка HTTP: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        const transactionId = data.transactionId;
        document.getElementById('myForm').innerHTML = `
                    <div>
                        <label for="idFrom">From card id:</label>
                        <input type="text" id="idFrom" name="idFrom" required>
                    </div>
                    <div>
                        <label for="idTo">To card id:</label>
                        <input type="text" id="idTo" name="idTo" required>
                    </div>
                    <div>
                        <label for="amount">Amount:</label>
                        <input type="number" id="amount" name="amount" step="0.01" required>
                    </div>
                    <p style="color: green;">Success</p>
                    <button type="submit">Transaction</button>
                `;
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('myForm').innerHTML = `
            <div>
                <label for="idFrom">From card id:</label>
                <input type="text" id="idFrom" name="idFrom" required>
            </div>
            <div>
                <label for="idTo">To card id:</label>
                <input type="text" id="idTo" name="idTo" required>
            </div>
            <div>
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" step="0.01" required>
            </div>
            <p style="color: red;">Error: ${error.message}</p>
            <button type="submit">Transaction</button>
        `;
    });
}
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
                <div style="border: 2px solid #000; background-color: rgba(255, 255, 255, 0.5); padding: 5px; margin: 5px;">
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
function loadDataOfAllTransactions(count) {
    fetch('/api/transaction/get_last_transactions/' + count, {
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
            const container = document.getElementById('all-transactions-data');
            for (var i=0; i<data.length; i++) {
                var transaction = data[i];
                container.innerHTML += `
                    <div style="border: 2px solid #000; background-color: rgba(255, 255, 255, 0.5); padding: 2px; margin: 2px;">
                    <p style="margin: 3px;"><strong>Data:</strong> ${transaction.dealTime}</p>
                    <p style="margin: 3px;"><strong>Amount:</strong> ${transaction.amount}</p>
                    </div>
                `
            }
        })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-transactions-data').innerHTML =
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
                <div style="border: 2px solid #000; background-color: rgba(255, 255, 255, 0.5); padding: 5px;">
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
function loadDataTransactionsByUserId(id) {
    fetch('/api/transaction/get_transactions_by_user_id/' + id, {
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
        const container = document.getElementById('all-user-transactions-data');
        for (var i=0; i<data.length; i++) {
            var transaction = data[i];
            container.innerHTML += `
                <div style="border: 2px solid #000; background-color: rgba(255, 255, 255, 0.5); padding: 2px; margin: 2px;">
                <p style="margin: 3px;"><strong>Data:</strong> ${transaction.dealTime}</p>
                <p style="margin: 3px;"><strong>Amount:</strong> ${transaction.amount}</p>
                </div>
            `
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-user-transactions-data').innerHTML =
            `<p style="color: red;">Error: ${error.message}</p>`;
    });
}