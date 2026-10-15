<script setup>
import {onMounted, ref} from "vue";

const clients = ref([]);
const loading = ref(true);
const error = ref("");

onMounted(async () => {
  try {
    const response = await fetch("http://localhost:8080/profile/personal");
    if (!response.ok) {
      throw new Error(`Ошибка сервера: ${response.status}`);
    }
    clients.value = await response.json();
  } catch (e) {
    error.value = "Не удалось загрузить клиентов. Проверь, запущен ли backend.";
    console.error(e);
  } finally {
    loading.value = false;
  }
});
</script>

<template>
  <main>
    <section>
      <p v-if="loading">Загрузка...</p>

      <p v-else-if="error" class="error">
        {{ error }}
      </p>

      <p v-else-if="clients.length === 0">
        Клиентов нет
      </p>

      <template v-else>
        <RouterLink
            v-for="client in clients"
            :key="client.idClient"
            :to="`/profile/${client.idClient}`"
            class="client-link"
        >
          <article class="client-card">
            <img
                :src="client.avatar || '/images/default-avatar.png'"
                :alt="`Аватар ${client.firstName}`"
                width="100"
            />

            <h3>{{ client.firstName }} {{ client.lastName }}</h3>
            <p>Отчество: {{ client.surname }}</p>
            <p>Email: {{ client.email }}</p>
            <p>Телефон: {{ client.number }}</p>
          </article>
        </RouterLink>
      </template>
    </section>
  </main>
</template>

<style scoped>
.error {
  color: crimson;
}
</style>