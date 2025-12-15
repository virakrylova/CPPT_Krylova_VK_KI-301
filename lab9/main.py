##################################################################################
# Copyright (c) 2023–2025 Lviv Polytechnic National University.
# All Rights Reserved.
#
# SPDX-License-Identifier: AFL-3.0
##################################################################################

from phone_pkg.mobile_phone import MobilePhone


if __name__ == "__main__":
    phone = MobilePhone(
        brand="Samsung",
        model="A52",
        os_name="Android",
        sim_slots=2,
        battery_percent=35,
        has_speaker=True
    )

    print(phone.get_info())
    phone.power_on()

    phone.enable_speaker()
    phone.toggle_mobile_data(True)

    phone.install_app("Telegram")
    phone.install_app("Chrome")
    phone.list_apps()

    phone.call("+380991234567")
    phone.hang_up()

    phone.charge(10)
    phone.power_off()
