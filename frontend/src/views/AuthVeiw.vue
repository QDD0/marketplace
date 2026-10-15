<script setup>

</script>

<template>
  <main>
    <div class="form-wrap">
      <div class="bg" aria-hidden="true">
        <span class="blob blob-1"></span>
        <span class="blob blob-2"></span>
        <span class="blob blob-3"></span>
        <span class="blob blob-4"></span>
      </div>

      <form action="/login" method="post" class="show-profile">
        <h2>Вход в аккаунт</h2>

        <div class="field">
          <label for="username">Логин или Email</label>
          <input type="text" id="username" name="username" required />
        </div>

        <div class="field">
          <div class="password-row">
            <label for="password">Пароль</label>
            <a href="#" class="forgot-password">Забыли пароль?</a>
          </div>
          <input type="password" id="password" name="password" required />
        </div>

        <button type="submit">Войти</button>

        <p class="create">Нет аккаунта? <router-link to="/auth/reg">Создать</router-link></p>
<!--        исправить стиль возврата и врег тоже-->
        <router-link to="/" class="back">Назад</router-link>
      </form>
    </div>
  </main>
</template>

<style scoped>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  min-height: 100vh;
  overflow: hidden;
  background: #eef3ff;
}

main {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-wrap {
  position: relative;
  padding: 80px;
}

.bg {
  position: absolute;
  top: -100px;
  right: -100px;
  bottom: -100px;
  left: -100px;
  z-index: 0;
  pointer-events: none;
}

.blob {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.7;
  will-change: transform, opacity;
}

.blob-1 {
  width: 320px;
  height: 320px;
  background: #5aa9ff;
  top: 0;
  left: 0;
  animation:
      move1 6s ease-in-out infinite,
      pulse1 4s ease-in-out infinite;
}

.blob-2 {
  width: 280px;
  height: 280px;
  background: #8ec5ff;
  bottom: 0;
  left: 10%;
  animation:
      move2 8s ease-in-out infinite,
      pulse2 5s ease-in-out infinite;
}

.blob-3 {
  width: 300px;
  height: 300px;
  background: #2563eb;
  top: 10%;
  right: 0;
  animation:
      move3 7s ease-in-out infinite,
      pulse3 4.5s ease-in-out infinite;
}

.blob-4 {
  width: 240px;
  height: 240px;

  background: #c7e2ff;

  bottom: 0;
  right: 10%;

  animation:
      move4 9s ease-in-out infinite,
      pulse4 6s ease-in-out infinite;
}

@keyframes move1 {
  0% {
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

  100% {
    transform: translate(0, 0) scale(1);
  }
}

@keyframes move2 {
  0% {
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

  100% {
    transform: translate(0, 0) scale(1);
  }
}

@keyframes move3 {
  0% {
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

  100% {
    transform: translate(0, 0) scale(1);
  }
}

@keyframes move4 {
  0% {
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

  100% {
    transform: translate(0, 0) scale(1);
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

.show-profile {
  position: relative;
  z-index: 1;

  width: 420px;
  min-height: 560px;

  padding: 30px 36px 40px;

  display: flex;
  flex-direction: column;
  justify-content: center;

  gap: 16px;

  background: rgba(255, 255, 255, 0.85);

  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);

  border-radius: 25px;

  box-shadow: 0 14px 53px 2px rgba(34, 60, 80, 0.25);
}

.show-profile h2 {
  margin: 0 0 10px;

  text-align: center;

  font-family: system-ui, sans-serif;

  color: #222;
}

.field {
  display: flex;
  flex-direction: column;

  gap: 6px;
}

.field-label-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.optional-note {
  color: #777;
  font-family: system-ui, sans-serif;
  font-size: 10px;
}

.password-row {
  display: flex;
  flex-direction: row;

  align-items: center;
  justify-content: space-between;
}

.show-profile label {
  font-family: system-ui, sans-serif;

  font-size: 14px;

  color: #555;
}

.forgot-password {
  color: #4a6cf7;

  font-size: 13px;

  text-decoration: none;

  font-family: system-ui, sans-serif;
}

.forgot-password:hover {
  color: black;

  text-decoration: underline;
}

.show-profile input {
  padding: 12px 14px;

  font-size: 15px;

  border: 1px solid #ccc;

  border-radius: 10px;

  outline: none;

  background: rgba(255, 255, 255, 0.7);

  transition:
      border-color 0.2s,
      box-shadow 0.2s;

  font-family: system-ui, sans-serif;
}

.show-profile input:focus {
  border-color: black;

  box-shadow: 0 0 0 3px rgba(165, 165, 165, 0.35);
}

.show-profile button {
  margin-top: 10px;

  padding: 12px;

  font-size: 16px;

  font-weight: 600;

  color: white;

  background-color: black;

  border: none;

  border-radius: 10px;

  cursor: pointer;

  transition:
      background-color 0.2s,
      transform 0.1s;

  font-family: system-ui, sans-serif;
}

.show-profile button:hover {
  background-color: rgb(75, 75, 75);

  transform: scale(0.98);
}

.create {
  margin-top: 2vh;

  text-align: center;

  color: #555;
}

.create a:hover {
  color: black;

  cursor: pointer;
}

.back {
  margin-top: 2vh;

  text-align: center;
}
</style>