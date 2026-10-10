<script setup>
import { onMounted, ref } from 'vue'

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
})
</script>

<template>
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
            {{ item.countItem > 0
              ? `В наличии: ${item.countItem} шт.`
              : 'Нет в наличии' }}
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