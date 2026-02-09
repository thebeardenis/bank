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