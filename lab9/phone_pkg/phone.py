##################################################################################
# Copyright (c) 2023–2025 Lviv Polytechnic National University.
# All Rights Reserved.
#
# SPDX-License-Identifier: AFL-3.0
##################################################################################

class Phone:
    """
    Базовий клас предметної області: Телефон.
    Реалізує базові можливості: дзвінок, завершення дзвінка, стан пристрою.
    """

    def __init__(self, brand: str, model: str, has_speaker: bool = True):
        self._brand = brand
        self._model = model
        self._has_speaker = has_speaker
        self._powered_on = False
        self._in_call = False
        self._current_number = None

    # ------------------- Службові/інформаційні методи -------------------

    def power_on(self) -> None:
        """Увімкнути телефон."""
        self._powered_on = True
        print(f"{self._brand} {self._model}: powered ON")

    def power_off(self) -> None:
        """Вимкнути телефон (якщо був дзвінок — завершуємо)."""
        if self._in_call:
            self.hang_up()
        self._powered_on = False
        print(f"{self._brand} {self._model}: powered OFF")

    def is_on(self) -> bool:
        """Повертає True, якщо телефон увімкнений."""
        return self._powered_on

    def get_info(self) -> str:
        """Повертає рядок з інформацією про телефон."""
        return f"Phone({self._brand} {self._model}, speaker={self._has_speaker})"

    # ------------------- Основна функціональність телефону -------------------

    def call(self, number: str) -> None:
        """
        Почати дзвінок на номер.
        Коректно відпрацьовує, якщо телефон вимкнений або вже є дзвінок.
        """
        if not self._powered_on:
            print("Cannot call: phone is OFF")
            return
        if self._in_call:
            print("Cannot call: already in a call")
            return

        self._in_call = True
        self._current_number = number
        print(f"Calling {number} from {self._brand} {self._model}...")

    def hang_up(self) -> None:
        """Завершити дзвінок."""
        if not self._in_call:
            print("No active call to hang up.")
            return

        print(f"Call with {self._current_number} ended.")
        self._in_call = False
        self._current_number = None

    def enable_speaker(self) -> None:
        """Увімкнути гучний зв’язок, якщо підтримується."""
        if not self._has_speaker:
            print("Speakerphone not supported on this phone.")
            return
        if not self._powered_on:
            print("Cannot enable speaker: phone is OFF")
            return
        print("Speakerphone enabled.")
