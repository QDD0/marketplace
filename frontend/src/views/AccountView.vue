<script setup>
import { ref, onMounted, watch } from 'vue'

const isDarkTheme = ref(localStorage.getItem('theme') === 'dark')

function applyTheme(value) {
  localStorage.setItem('theme', value ? 'dark' : 'light')
}

onMounted(() => applyTheme(isDarkTheme.value))

watch(isDarkTheme, (value) => applyTheme(value))
</script>

<template>
  <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
  />

  <div :class="['account-page', { 'dark-theme': isDarkTheme }]">
    <div class="bg" aria-hidden="true">
      <div class="blob-field">
        <span class="blob blob-1"></span>
        <span class="blob blob-2"></span>
        <span class="blob blob-3"></span>
        <span class="blob blob-4"></span>
      </div>
    </div>

    <main class="main-window">
      <section>
        <header class="profile">
          <h1>Аккаунт</h1>
          <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBfP45M1iHnWF9lzdranxUiSDodPZvb2B7Smh8LoynGQ&s=10"
              alt="Аватар пользователя"
              class="avatar"
          />
          <p class="info">Your name</p>
          <p class="info">your email</p>
          <button class="btn-edit">Редактировать</button>
        </header>

        <nav class="menu-group" aria-label="Покупки">
          <a href="">
            <i class="fa-solid fa-clock-rotate-left" aria-hidden="true"></i>
            История покупок
          </a>
          <a href="">
            <i class="fa-solid fa-bag-shopping" aria-hidden="true"></i>
            Корзина
          </a>
        </nav>

        <nav class="menu-group" aria-label="Управление">
          <a href="">
            <i class="fa-solid fa-gear" aria-hidden="true"></i>
            Настройки
          </a>
          <a href="">
            <i class="fa-solid fa-users-gear" aria-hidden="true"></i>
            Управление персоналом
          </a>
          <a href="">
            <i class="fa-solid fa-cubes" aria-hidden="true"></i>
            Управление товарами
          </a>
          <div class="theme-setting">
            <span>Смена темы</span>
            <label class="switch">
              <input
                  v-model="isDarkTheme"
                  type="checkbox"
                  id="theme-toggle"
                  aria-label="Переключить тёмную тему"
              />
              <span class="slider"></span>
            </label>
          </div>
        </nav>

        <router-link to="/" class="go-back">Назад</router-link>
        <a href="" class="go-back">Выйти</a>
        <button class="btn-delete">Удалить</button>
      </section>
    </main>
  </div>
</template>

<style scoped>
* {
  box-sizing: border-box;
}

.account-page {
  --bg-color: #f3f6fc;
  --text-color: #1e293b;
  --surface-color: #fff;
  --menu-color: rgba(202, 200, 200, 0.3);
  --button-color: #000;
  --button-text-color: #fff;

  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  width: 100%;
  padding: 24px;
  background: var(--bg-color);
  color: var(--text-color);
  font-family: Arial, sans-serif;
}

.account-page.dark-theme {
  --bg-color: #000;
  --text-color: #f1f5f9;
  --surface-color: #111827;
  --menu-color: rgba(255, 255, 255, 0.08);
  --button-color: #334155;
  --button-text-color: #f1f5f9;
}

.bg {
  position: fixed;
  inset: -100px;
  z-index: 0;
  overflow: hidden;
  pointer-events: none;
}

.blob-field {
  position: relative;
  width: 100%;
  height: 100%;
}

.blob {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.7;
  will-change: transform, opacity;
}

.blob-1 {
  top: 0;
  left: 0;
  width: 320px;
  height: 320px;
  background: #5aa9ff;
  animation: move1 6s ease-in-out infinite,
  pulse1 4s ease-in-out infinite;
}

.blob-2 {
  bottom: 0;
  left: 10%;
  width: 280px;
  height: 280px;
  background: #8ec5ff;
  animation: move2 8s ease-in-out infinite,
  pulse2 5s ease-in-out infinite;
}

.blob-3 {
  top: 10%;
  right: 0;
  width: 300px;
  height: 300px;
  background: #2563eb;
  animation: move3 7s ease-in-out infinite,
  pulse3 4.5s ease-in-out infinite;
}

.blob-4 {
  right: 10%;
  bottom: 0;
  width: 240px;
  height: 240px;
  background: #c7e2ff;
  animation: move4 9s ease-in-out infinite,
  pulse4 6s ease-in-out infinite;
}

.main-window {
  position: relative;
  z-index: 1;
  width: min(100%, 500px);
  padding: 32px;
  border-radius: 24px;
  background: var(--surface-color);
  box-shadow: 0 24px 52px rgba(34, 60, 80, 0.16);
}

.profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  margin-bottom: 28px;
  text-align: center;
}

.profile h1 {
  margin: 0;
  font-size: 24px;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.info {
  margin: 0;
  font-size: 1rem;
  line-height: 1;
  font-weight: 500;
  color: var(--text-color);
}

.btn-edit {
  padding: 10px 18px;
  border: 0;
  border-radius: 10px;
  color: var(--button-text-color);
  background: var(--button-color);
  font: inherit;
  cursor: pointer;
  transition: box-shadow 0.2s linear;
}

.btn-edit:hover,
.btn-edit:focus-visible {
  box-shadow: 0 0 0 2px #fff,
  0 0 0 4px #3c82f8;
}

.menu-group {
  display: flex;
  flex-direction: column;
  gap: 4px;
  padding: 8px;
  border-radius: 15px;
  background: var(--menu-color);
}

.menu-group + .menu-group {
  margin-top: 16px;
}

.menu-group a {
  display: flex;
  align-items: center;
  gap: 12px;
  min-height: 44px;
  padding: 10px 12px;
  border-radius: 10px;
  color: var(--text-color);
  text-decoration: none;
  transition: background-color 0.2s ease;
}

.menu-group a:hover,
.menu-group a:focus-visible {
  background: rgba(0, 0, 0, 0.08);
}

.go-back {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 44px;
  margin: 20px 0 0;
  padding: 10px 16px;
  border: 1px solid rgb(100 116 139 / 30%);
  border-radius: 10px;
  color: var(--text-color);
  text-decoration: none;
  transition: color 0.2s ease,
  background-color 0.2s ease,
  border-color 0.2s ease,
  box-shadow 0.2s ease;
}

.go-back + .go-back {
  margin-top: 8px;
  border-color: transparent;
  color: var(--button-text-color);
  background: var(--button-color);
}

.go-back:hover {
  border-color: #3c82f8;
  background-color: rgb(60 130 248 / 10%);
}

.go-back + .go-back:hover {
  border-color: transparent;
  background-color: #2563eb;
}

.go-back:focus-visible,
.btn-delete:focus-visible {
  outline: 3px solid #3c82f8;
  outline-offset: 3px;
}

.btn-delete {
  display: block;
  width: 100%;
  min-height: 44px;
  margin-top: 8px;
  padding: 10px 16px;
  border: 1px solid #dc2626;
  border-radius: 10px;
  color: #fff;
  background: #dc2626;
  font: inherit;
  cursor: pointer;
  transition: background-color 0.2s ease,
  border-color 0.2s ease,
  box-shadow 0.2s ease;
}

.btn-delete:hover {
  border-color: #b91c1c;
  background-color: #b91c1c;
}

.theme-setting {
  display: flex;
  align-items: center;
  justify-content: space-between;
  min-height: 44px;
  padding: 8px 12px;
}

.switch {
  position: relative;
  display: inline-flex;
  align-items: center;
  width: 58px;
  height: 26px;
  flex-shrink: 0;
  cursor: pointer;
}

.switch input {
  position: absolute;
  inset: 0;
  z-index: 2;
  width: 100%;
  height: 100%;
  margin: 0;
  cursor: pointer;
  opacity: 0;
}

.slider {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
  border-radius: 999px;
  background: linear-gradient(135deg, #cbd5e1, #94a3b8);
  box-shadow: inset 0 2px 5px rgb(15 23 42 / 18%);
  transition: background 0.3s ease,
  box-shadow 0.3s ease;
}

.slider:before {
  position: absolute;
  content: "";
  width: 20px;
  height: 20px;
  left: 3px;
  top: 3px;
  background-color: white;
  border-radius: 50%;
  box-shadow: 0 2px 6px rgb(15 23 42 / 25%);
  transition: transform 0.3s ease,
  box-shadow 0.3s ease;
}

.switch input:checked + .slider {
  background: linear-gradient(135deg, red, orange);
  box-shadow: inset 0 2px 5px rgb(49 46 129 / 25%);
}

.switch input:focus-visible + .slider {
  outline: 3px solid #a78bfa;
  outline-offset: 3px;
}

.switch input:checked + .slider:before {
  transform: translateX(32px);
  box-shadow: 0 2px 8px rgb(30 27 75 / 35%);
}

.switch:hover .slider:before {
  box-shadow: 0 3px 9px rgb(15 23 42 / 30%);
}

@keyframes move1 {
  0%,
  100% {
    transform: translate(0, 0) scale(1);
  }

  25% {
    transform: translate(100px, 70px) scale(1.2);
  }

  50% {
    transform: translate(40px, 150px) scale(0.85);
  }

  75% {
    transform: translate(-80px, 100px) scale(1.15);
  }
}

@keyframes move2 {
  0%,
  100% {
    transform: translate(0, 0) scale(1);
  }

  25% {
    transform: translate(100px, -90px) scale(1.25);
  }

  50% {
    transform: translate(-30px, -60px) scale(0.9);
  }

  75% {
    transform: translate(70px, 30px) scale(1.2);
  }
}

@keyframes move3 {
  0%,
  100% {
    transform: translate(0, 0) scale(1);
  }

  25% {
    transform: translate(-100px, 70px) scale(1.2);
  }

  50% {
    transform: translate(-30px, -80px) scale(0.9);
  }

  75% {
    transform: translate(-120px, -20px) scale(1.15);
  }
}

@keyframes move4 {
  0%,
  100% {
    transform: translate(0, 0) scale(1);
  }

  30% {
    transform: translate(-100px, -70px) scale(1.3);
  }

  60% {
    transform: translate(50px, -30px) scale(0.9);
  }

  80% {
    transform: translate(-40px, 40px) scale(1.15);
  }
}

@keyframes pulse1 {
  0%,
  100% {
    opacity: 0.55;
  }

  50% {
    opacity: 0.85;
  }
}

@keyframes pulse2 {
  0%,
  100% {
    opacity: 0.45;
  }

  50% {
    opacity: 0.75;
  }
}

@keyframes pulse3 {
  0%,
  100% {
    opacity: 0.5;
  }

  50% {
    opacity: 0.85;
  }
}

@keyframes pulse4 {
  0%,
  100% {
    opacity: 0.4;
  }

  50% {
    opacity: 0.7;
  }
}

@media (prefers-reduced-motion: reduce) {
  .blob {
    animation: none;
  }

  .slider,
  .slider:before,
  .account-page {
    transition: none;
  }
}
</style>