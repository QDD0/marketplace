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
  <main>
    <section>
      <p v-if="loading">Загрузка...</p>

      <p v-else-if="error" class="error">{{ error }}</p>

      <p v-else-if="items.length===0">Товаров нет</p>

      <template v-else>
        <router-link
            v-for="item in items"
            :key="item.idItem"
            to="`/items/${item.idItem}`"
            class="item-link"
        >
          <article class="item-card">
            <!--            <img-->
            <!--                :src="item.avatar || '/images/default-avatar.png'"-->
            <!--                :alt="`Аватар ${client.firstName}`"-->
            <!--                width="100"-->
            <!--            >-->
            <div class="product-info">
              <h2>{{ item.nameItem }}</h2>

              <p class="description">
                {{ item.description || 'Описание отсутствует' }}
              </p>

              <p class="price">
                {{ item.price.toLocaleString('ru-RU') }} ₽
              </p>

              <p class="stock">
                {{
                  item.countItem > 0
                      ? `В наличии: ${item.countItem} шт.`
                      : 'Нет в наличии'
                }}
              </p>

              <button
                  class="buy-button"
                  :disabled="item.countItem === 0"
              >
                {{ item.countItem > 0 ? 'В корзину' : 'Нет в наличии' }}
              </button>
            </div>
          </article>
        </router-link>
      </template>
    </section>
  </main>
</template>

<style scoped>

</style>