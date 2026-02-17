function formatDateTime(date) {
    return date.toLocaleString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false
    });
}
function loadDataCardsByTransactionId(id) {
    fetch('/api/transaction/get_last_transactions/' + id, {
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
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-transactions-data').innerHTML =
            `<p style="color: red; padding: 15px;">Ошибка: ${error.message}</p>`;
    });
}

function transaction(cardIdFrom, cardIdTo, amount) {
    const formData = new URLSearchParams();
    formData.append('cardIdFrom', cardIdFrom);
    formData.append('cardIdTo', cardIdTo);
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
        const transactionId = data.transaction.id;
        document.getElementById('myForm').innerHTML = `
                    <div>
                        <label for="cardIdFrom">From card id:</label>
                        <input type="number" id="cardIdFrom" name="cardIdFrom" required>
                    </div>
                    <div>
                        <label for="cardIdTo">To card id:</label>
                        <input type="number" id="cardIdTo" name="cardIdTo" required>
                    </div>
                    <div>
                        <label for="amount">Amount:</label>
                        <input type="number" id="amount" name="amount" step="0.01" required>
                    </div>
                    <p style="color: green;">Success</p>
                    <a href="http://localhost:8080/view_transaction/${transactionId}">View last transaction</a><br>
                    <button type="submit">Transaction</button>
                `;
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('myForm').innerHTML = `
            <div>
                <label for="cardIdFrom">From card id:</label>
                <input type="number" id="cardIdFrom" name="cardIdFrom" required>
            </div>
            <div>
                <label for="cardIdTo">To card id:</label>
                <input type="number" id="cardIdTo" name="cardIdTo" required>
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
        container.innerHTML = '';

        data.forEach(transaction => {
            const date = new Date(transaction.dealTime);
            const formattedDate = formatDateTime(date);

            container.innerHTML += `
                <div  style="border: 2px solid #000; border-radius: 4px; background-color: rgba(255, 255, 255, 0.5); padding: 2px; margin: 2px;">
                    <p style="margin: 3px;"><strong>Дата:</strong> ${formattedDate}</p>
                    <p style="margin: 3px;"><strong>Сумма:</strong> ${transaction.amount}</p>
                </div>
            `;
        });
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-transactions-data').innerHTML =
            `<p style="color: red; padding: 15px;">Ошибка: ${error.message}</p>`;
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
        container.innerHTML = '';

                data.forEach(transaction => {
                    const date = new Date(transaction.dealTime);
                    const formattedDate = formatDateTime(date);

                    container.innerHTML += `
                        <div  style="border: 2px solid #000; border-radius: 4px; background-color: rgba(255, 255, 255, 0.5); padding: 2px; margin: 2px;">
                            <p style="margin: 3px;"><strong>Дата:</strong> ${formattedDate}</p>
                            <p style="margin: 3px;"><strong>Сумма:</strong> ${transaction.amount}</p>
                        </div>
                    `;
                });
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-user-transactions-data').innerHTML =
            `<p style="color: red;">Error: ${error.message}</p>`;
    });
}

function loadDataTransactionsByCardId(id) {
    fetch('/api/transaction/get_transactions_by_card_id/' + id, {
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
        const container = document.getElementById('all-card-transactions-data');
        container.innerHTML = '';

                data.forEach(transaction => {
                    const date = new Date(transaction.dealTime);
                    const formattedDate = formatDateTime(date);

                    container.innerHTML += `
                        <div  style="border: 2px solid #000; border-radius: 4px; background-color: rgba(255, 255, 255, 0.5); padding: 2px; margin: 2px;">
                            <p style="margin: 3px;"><strong>Дата:</strong> ${formattedDate}</p>
                            <p style="margin: 3px;"><strong>Сумма:</strong> ${transaction.amount}</p>
                        </div>
                    `;
                });
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('all-card-transactions-data').innerHTML =
            `<p style="color: red;">Error: ${error.message}</p>`;
    });
}

function loadDataByTransactionId(id) {
    fetch('/api/transaction/get_transaction_by_id/' + id, {
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
        const container = document.getElementById('transaction-data');
        container.innerHTML = '';
        const date = new Date(data.dealTime);
        const formattedDate = formatDateTime(date);

        container.innerHTML += `
            <div  style="border: 2px solid #000; border-radius: 4px; background-color: rgba(255, 255, 255, 0.5); padding: 2px; margin: 2px;">
                <p style="margin: 3px;"><strong>Карта отправителя:</strong> <a href="http://localhost:8080/view_card_transactions/${data.cardFromId}"> CardFrom </a></p>
                <p style="margin: 3px;"><strong>Карта получателя:</strong> <a href="http://localhost:8080/view_card_transactions/${data.cardToId}"> CardTo </a></p>
                <p style="margin: 3px;"><strong>Дата совершения транзакции:</strong> ${formattedDate}</p>
                <p style="margin: 3px;"><strong>Сумма перевода:</strong> ${data.amount}</p>
            </div>
        `;
    })
    .catch(error => {
        console.error('Ошибка:', error);
        document.getElementById('transaction-data').innerHTML =
            `<p style="color: red;">Error: ${error.message}</p>`;
    });
}