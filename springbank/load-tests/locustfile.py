from locust import FastHttpUser, task
import random


class SpringBankUser(FastHttpUser):
    host = "http://localhost:8080"

    def __init__(self, parent):
        self.account_id = None
        super().__init__(parent)

    def on_start(self):
        with self.rest("POST", "/accounts", json={"balance": 1000}) as res:
            self.account_id = res.js["id"]

    @task
    def transfer(self):
        with self.rest("GET", "/accounts") as res:
            if len(res.js) < 2:
                return
            to = random.choice(res.js)
            while to == self.account_id:
                to = random.choice(res.js)
        amount = round(random.uniform(0.01, 9.99), 2)
        with self.rest("POST", "/transfers", json={"from": self.account_id, "to": to, "amount": amount}) as res:
            pass
