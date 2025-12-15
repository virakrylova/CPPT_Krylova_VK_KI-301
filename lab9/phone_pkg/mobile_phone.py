##################################################################################
# Copyright (c) 2023–2025 Lviv Polytechnic National University.
# All Rights Reserved.
#
# SPDX-License-Identifier: AFL-3.0
##################################################################################

from .phone import Phone


class MobilePhone(Phone):
    """
    Похідний клас: Мобільний телефон.
    Розширює Phone додатковими властивостями: SIM, батарея, мобільні дані, додатки.
    """

    def __init__(
        self,
        brand: str,
        model: str,
        os_name: str,
        sim_slots: int = 1,
        battery_percent: int = 100,
        has_speaker: bool = True
    ):
        super().__init__(brand, model, has_speaker)
        self.__os_name = os_name
        self.__sim_slots = sim_slots
        self.__battery_percent = max(0, min(100, battery_percent))
        self.__mobile_data_on = False
        self.__apps = []

    # ------------------- Додаткові методи мобільного телефону -------------------

    def get_os(self) -> str:
        """Повертає назву ОС."""
        return self.__os_name

    def get_battery(self) -> int:
        """Повертає заряд батареї у %."""
        return self.__battery_percent

    def charge(self, amount: int) -> None:
        """Підзарядити телефон на amount%."""
        self.__battery_percent = max(0, min(100, self.__battery_percent + amount))
        print(f"Battery: {self.__battery_percent}%")

    def toggle_mobile_data(self, on: bool) -> None:
        """Увімкнути/вимкнути мобільні дані."""
        if not self.is_on():
            print("Cannot change mobile data: phone is OFF")
            return
        self.__mobile_data_on = on
        state = "ON" if on else "OFF"
        print(f"Mobile data: {state}")

    def install_app(self, app_name: str) -> None:
        """Встановити додаток (простий список)."""
        if not self.is_on():
            print("Cannot install app: phone is OFF")
            return
        if app_name in self.__apps:
            print(f"App '{app_name}' already installed.")
            return
        self.__apps.append(app_name)
        print(f"App '{app_name}' installed.")

    def list_apps(self) -> None:
        """Вивести список встановлених додатків."""
        if not self.__apps:
            print("No apps installed.")
            return
        print("Installed apps:")
        for a in self.__apps:
            print(f"- {a}")

    # ------------------- Перевизначення базової поведінки -------------------

    def call(self, number: str) -> None:
        """
        Перевизначений дзвінок:
        - якщо батарея 0% — дзвінок неможливий
        - при старті дзвінка витрачається 1% батареї (демо-логіка)
        """
        if self.__battery_percent <= 0:
            print("Cannot call: battery is empty.")
            return

        super().call(number)

        # якщо дзвінок реально стартував — зменшуємо батарею
        # (ознака: у базовому класі дзвінок став активним)
        # тут без доступу до приватних полів, тому робимо просту демо-витрату:
        if self.is_on():
            self.__battery_percent = max(0, self.__battery_percent - 1)
            print(f"Battery after call start: {self.__battery_percent}%")
