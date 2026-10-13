<script setup>
import {onMounted, ref} from 'vue'

const items = ref([])
const loading = ref(true)
const error = ref('')

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080')

    if (!response.ok) {
      throw new Error(`Ошибка сервера: ${response.status}`)
    }

    items.value = await response.json()
  } catch (e) {
    error.value =
        'Не удалось загрузить товары. Проверь, запущен ли backend.'
    console.error(e)
  } finally {
    loading.value = false
  }

  const track = document.querySelector(".slider-track");
  const slides = document.querySelectorAll(".slide");

  let currentIndex = 0;
  const realSlidesCount = slides.length - 1;
  const time = 3000;

  function nextSlide() {
    currentIndex++;
    track.style.transform = `translateX(-${currentIndex * 100}%)`;

    if (currentIndex === realSlidesCount) {
      setTimeout(() => {
        track.style.transition = "none";
        currentIndex = 0;
        track.style.transform = "translateX(0)";

        void track.offsetHeight;

        track.style.transition = "transform 0.7s ease-in-out";
      }, 2000);
    }
  }

  setInterval(nextSlide, time);

})
</script>

<template>
  <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
  />
  <header>
    <nav class="nav-bar">
      <div class="nav-left">
        <h3 class="logo">
          <a href="" style="font-size: 35px;">MoDel</a>
        </h3>
      </div>

      <div class="nav-center">
        <div class="catalog-menu">
          <a href="#" class="catalog">
            <img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAXklEQVR4nO2UMQrAMAhF3xmaKzc5c50sgS4OacShhNQHTj74DirsSAME0EEJUAOuoTeOlyEKcAVcQ59ghgbcDCFDkGf1vCtcnK6hOg7sDLjf0/Kt/PNONEMG5FtZmBsi6Ig1lLiwgQAAAABJRU5ErkJggg=="
                alt="Иконка каталога"
            />
            Каталог
          </a>
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

  <section class="news-content">
    <div class="slider">
      <div class="slider-track">
        <div class="slide">
          <figure class="slide-figure">
            <img
                src="https://consumer.huawei.com/dam/content/dam/huawei-cbg-site/common/mkt/homepage-x/new/0902-2026-huawei-innovative-product-launch/herobanner/watch-gt7-pro-pc.jpg"
                alt="Смарт-часы Huawei Watch GT 7 Pro на тёмном фоне со спортивным дизайном и высоким качеством изображения"
            />
            <figcaption class="slide-caption">
              <span class="slide-tag">Новинка</span>
              <strong>Huawei Watch GT 7 Pro</strong>
              <small
              >Технологии для активного образа жизни и точного контроля
                здоровья.</small
              >
            </figcaption>
          </figure>
        </div>
        <div class="slide">
          <figure class="slide-figure">
            <img
                src="https://storage-asset.msi.com/global/picture/image/feature/PC-Case/mpg-vixta-300r-pz-w/kv/kv-bg.webp"
                alt="Игровой компьютер MSI в стильном корпусе с подсветкой и мощной производительностью"
            />
            <figcaption class="slide-caption">
              <span class="slide-tag">Спецпредложение</span>
              <strong>Игровые компьютеры от MSI</strong>
              <small
              >От 200 000 рублей — мощность для работы, стриминга и
                игр.</small
              >
            </figcaption>
          </figure>
        </div>
        <div class="slide">
          <figure class="slide-figure">
            <img
                src="https://storage-asset.msi.com/global/picture/banner/banner_1790731708bff59f5bccbdec4c20360a87bd48f39f.jpeg"
                alt="Рекламный баннер MSI с игровой тематикой и яркой цветовой подсветкой"
            />
            <figcaption class="slide-caption">
              <span class="slide-tag">Эволюция</span>
              <strong>MSI в новой игровой эпохе</strong>
              <small
              >Скорость, стиль и производительность в одном
                решении.</small
              >
            </figcaption>
          </figure>
        </div>
        <div class="slide">
          <figure class="slide-figure">
            <img
                src="https://www.apple.com/v/iphone-18-pro/c/images/overview/aio/hero__elo7tt0mnqy6_large.jpg"
                alt="Новые iPhone Pro на рекламном баннере с премиальным дизайном и современным интерфейсом"
            />
            <figcaption class="slide-caption">
              <span class="slide-tag">Премиум</span>
              <strong>Новые iPhone Pro</strong>
              <small
              >Идеальная связь технологий, камеры и производительности в
                одном устройстве.</small
              >
            </figcaption>
          </figure>
        </div>
        <div class="slide">
          <figure class="slide-figure">
            <img
                src="https://i02.appmifile.com/335_operator_global/31/08/2026/2517c2befb6a0a7d355a42658f74fcf2.jpg"
                alt="Смартфон Xiaomi в стильной рекламной кампании с яркими цветовыми акцентами"
            />
            <figcaption class="slide-caption">
              <span class="slide-tag">Тренд</span>
              <strong>Xiaomi — в центре внимания</strong>
              <small
              >Передовые функции, стильный дизайн и отличная
                автономность.</small
              >
            </figcaption>
          </figure>
        </div>
        <div class="slide">
          <figure class="slide-figure">
            <img
                src="https://storage-asset.msi.com/global/picture/banner/banner_1781771327154ac5faf66cda1a5dc5b67d070c86f8.jpeg"
                alt="Дизайнерский баннер MSI с продукцией для геймеров и творцов"
            />
            <figcaption class="slide-caption">
              <span class="slide-tag">Эксклюзив</span>
              <strong> для тех, кто любит мощь</strong>
              <small>Смелые решения для игр, работы и креатива.</small>
            </figcaption>
          </figure>
        </div>
      </div>
    </div>
  </section>

  <main class="container">
    <p v-if="loading" class="message">
      Загружаем товары...
    </p>

    <p v-else-if="error" class="message error">
      {{ error }}
    </p>

    <section v-else class="products">
      <article
          v-for="item in items"
          :key="item.idItem"
          class="product-card"
      >
        <div class="product-image">
          <span>Нет изображения</span>
        </div>

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

      <p v-if="items.length === 0" class="message">
        Товаров пока нет
      </p>
    </section>
  </main>
</template>