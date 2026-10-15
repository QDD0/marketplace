<script setup>

import {onMounted, ref} from "vue";

const items = ref([]);
const loading = ref(true)
const error = ref('')

onMounted(async () => {
  try {
    const response = await fetch("http://localhost:8080/catalog");

    if (!response.ok) {
      throw new Error('Ошибка сервера: ${response.status}');
    }
    items.value = await response.json();
  } catch (e) {
    error.value = "Не удалось загрузить клиентов. Проверь, запущен ли backend.";
    console.error(e);
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
  />
  <header>
    <nav class="nav-bar">
      <div class="nav-left">
        <router-link to="/" class="logo">
          <a href="" style="font-size: 35px;">MoDel</a>
        </router-link>
      </div>

      <div class="nav-center">
        <div class="catalog-menu">
          <router-link to="/catalog" href="#" class="catalog">
            <img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAXklEQVR4nO2UMQrAMAhF3xmaKzc5c50sgS4OacShhNQHTj74DirsSAME0EEJUAOuoTeOlyEKcAVcQ59ghgbcDCFDkGf1vCtcnK6hOg7sDLjf0/Kt/PNONEMG5FtZmBsi6Ig1lLiwgQAAAABJRU5ErkJggg=="
                alt="Иконка каталога"
            />
            Каталог
          </router-link>
          <div class="catalog-dropdown" id="catalog-menu">
            <section class="catalog-column">
              <h2>Электроника</h2>
              <a href="#">Смартфоны и гаджеты</a>
              <a href="#">Компьютеры и ноутбуки</a>
              <a href="#">Телевизоры и аудио</a>
              <a href="#">Фото и видеотехника</a>
            </section>
            <section class="catalog-column">
              <h2>Техника для дома</h2>
              <a href="#">Все пылесосы</a>
              <a href="#">Освещение и LED лампы</a>
              <a href="#">Кондиционеры</a>
              <a href="#">Утюги</a>
            </section>
            <section class="catalog-column">
              <h2>Дом и ремонт</h2>
              <a href="#">Мебель</a>
              <a href="#">Товары для дома</a>
              <a href="#">Освещение</a>
              <a href="#">Инструменты и ремонт</a>
            </section>
            <section class="catalog-column">
              <h2>Посуда</h2>
              <a href="#">Наборы посуды</a>
              <a href="#">Посудомойки</a>
              <a href="#">Вытяжки</a>
              <a href="#">Плиты и чайники</a>
            </section>
          </div>
        </div>

        <form action="/search" method="get" class="find-item">
          <input type="search" placeholder="поиск" aria-label="Поиск"/>
          <button type="submit" aria-label="Найти">
            <i class="fa-brands fa-sistrix" aria-hidden="true"></i>
          </button>
        </form>
      </div>

      <ul class="nav-right">
        <li>
          <router-link to="/profile" class="prof-btn" aria-label="Профиль" title="Профиль">
            <i class="fa-regular fa-user icon-outline"></i>
            <i class="fa-solid fa-user icon-filled"></i>
          </router-link>
        </li>
        <li>
          <a
              href="#"
              class="fav-btn"
              aria-label="Избранное"
              title="Избранное"
          >
            <i class="fa-regular fa-heart icon-outline"></i>
            <i class="fa-solid fa-heart icon-filled"></i>
          </a>
        </li>
        <li>
          <a href="#" class="cart-btn" aria-label="Корзина" title="Корзина">
            <i class="fa-solid fa-cart-shopping" aria-hidden="true"></i>
          </a>
        </li>
        <li>
          <router-link to="/auth" class="login">войти</router-link>
        </li>
      </ul>
    </nav>
  </header>

  <main class="catalog-page">
    <div class="promo-video-wrap">
      <video
          class="promo-video"
          src="https://images.samsung.com/is/content/samsung/assets/us/offer/samsung-week/20261010/n26_sw_2h_f01_ft03_scenariokv_01_work_pc_1440x500.mp4"
          autoplay muted playsinline loop preload="auto"
      ></video>

      <div class="promo-overlay">
        <h2 class="promo-title">Samsung Week</h2>
        <p class="promo-subtitle">Скидки до 40% на технику</p>
        <router-link to="/catalog" class="promo-button">Смотреть</router-link>
      </div>
    </div>

    <section>
      <p v-if="loading" class="catalog-status">Загрузка...</p>

      <p v-else-if="error" class="catalog-status error">{{ error }}</p>

      <p v-else-if="items.length===0" class="catalog-status">Товаров нет</p>

      <div v-else class="catalog-grid">
        <router-link
            v-for="item in items"
            :key="item.idItem"
            :to="`/items/${item.idItem}`"
            class="item-link"
        >
          <article class="item-card">
            <div class="item-image" aria-hidden="true">
              <i class="fa-solid fa-bag-shopping"></i>
            </div>

            <div class="item-body">
              <h2 class="item-title">{{ item.nameItem }}</h2>

              <p class="item-description">
                {{ item.description || 'Описание отсутствует' }}
              </p>

              <div class="item-meta">
                <p class="item-price">
                  {{ item.price.toLocaleString('ru-RU') }} ₽
                </p>

                <p class="item-stock" :class="{ out: item.countItem === 0 }">
                  {{
                    item.countItem > 0
                        ? `В наличии: ${item.countItem} шт.`
                        : 'Нет в наличии'
                  }}
                </p>
              </div>

              <button
                  class="buy-button"
                  :disabled="item.countItem === 0"
                  type="button"
              >
                {{ item.countItem > 0 ? 'В корзину' : 'Нет в наличии' }}
              </button>
            </div>
          </article>
        </router-link>
      </div>
    </section>
  </main>
</template>

<style scoped>
.catalog-page {
  max-width: 1200px;
  margin: 32px auto 64px;
  padding: 0 24px;
}

.catalog-status {
  font-size: 1rem;
  color: #444;
  padding: 20px 0;
}

.catalog-status.error {
  color: #b12727;
}

.catalog-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 24px;
}

.item-link {
  display: block;
  color: inherit;
  text-decoration: none;
}

.item-card {
  display: flex;
  flex-direction: column;
  height: 100%;
  min-height: 390px;
  padding: 14px;
  border: 1px solid #e7e7e7;
  border-radius: 22px;
  background: #fff;
  box-shadow: 0 14px 30px rgb(17 17 17 / 8%);
  transition: transform 0.24s ease,
  box-shadow 0.24s ease,
  border-color 0.24s ease;
}

.item-card:hover {
  transform: translateY(-4px);
  border-color: #d9d9d9;
  box-shadow: 0 20px 40px rgb(17 17 17 / 12%);
}

.item-image {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 190px;
  border-radius: 16px;
  background: linear-gradient(135deg, #f3f3f3 0%, #e5e5e5 100%);
  color: #b7b7b7;
  font-size: 2.8rem;
}

.item-body {
  display: flex;
  flex: 1;
  flex-direction: column;
  gap: 12px;
  margin-top: 14px;
}

.item-title {
  margin: 0;
  font-size: 1.15rem;
  line-height: 1.35;
  color: #131313;
}

.item-description {
  margin: 0;
  color: #666;
  font-size: 0.92rem;
  line-height: 1.5;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}

.item-meta {
  display: flex;
  align-items: end;
  justify-content: space-between;
  gap: 12px;
  margin-top: auto;
}

.item-price {
  margin: 0;
  font-size: 1.55rem;
  font-weight: 800;
  letter-spacing: -0.03em;
  color: #101010;
}

.item-stock {
  margin: 0;
  font-size: 0.8rem;
  font-weight: 700;
  color: #1d7a4f;
  text-align: right;
}

.item-stock.out {
  color: #8a8a8a;
}

.buy-button {
  width: 100%;
  margin-top: 4px;
  border: none;
  border-radius: 12px;
  background: #111;
  color: #fff;
  padding: 12px 16px;
  font-size: 0.96rem;
  font-weight: 700;
  cursor: pointer;
  transition: background 0.2s ease, transform 0.2s ease;
}

.buy-button:hover:not(:disabled) {
  background: #2d2d2d;
  transform: translateY(-1px);
}

.buy-button:disabled {
  background: #d7d7d7;
  color: #666;
  cursor: not-allowed;
}

.promo-video-wrap {
  position: relative;
  width: 100%;
  margin-bottom: 32px;
  border-radius: 22px;
  overflow: hidden;
  background: #000;
  box-shadow: 0 14px 30px rgb(17 17 17 / 8%);
  line-height: 0;
}

.promo-video {
  display: block;
  width: 100%;
  height: auto;
  aspect-ratio: 1440 / 500;
  object-fit: cover;
}

.promo-overlay {
  position: absolute;
  inset: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  gap: 12px;
  padding: 0 60px;
  line-height: normal;
  color: #fff;
  background: linear-gradient(
      90deg,
      rgba(0, 0, 0, 0.55) 0%,
      rgba(0, 0, 0, 0.15) 50%,
      rgba(0, 0, 0, 0) 100%
  );
}

.promo-title {
  margin: 0;
  font-size: 2.4rem;
  font-weight: 800;
  letter-spacing: -0.03em;
}

.promo-subtitle {
  margin: 0;
  font-size: 1.1rem;
  opacity: 0.9;
}

.promo-button {
  margin-top: 8px;
  padding: 12px 24px;
  background: #fff;
  color: #111;
  border-radius: 999px;
  font-weight: 700;
  text-decoration: none;
  transition: transform 0.2s ease, background 0.2s ease;
}

.promo-button:hover {
  background: #f0f0f0;
  transform: translateY(-1px);
}
</style>