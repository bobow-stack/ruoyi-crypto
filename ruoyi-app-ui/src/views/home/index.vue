<template>
  <div class="page">
    <header class="hero">
      <div class="brand">
        <div class="logo">RX</div>
        <div>
          <div class="title">RuoYi Exchange</div>
          <div class="subtitle">Market · Trade · Assets</div>
        </div>
      </div>
      <div class="asset-card">
        <div class="asset-label">总资产（{{ overview.asset.asset || 'USDT' }}）</div>
        <div class="asset-value">{{ fmt(overview.asset.totalEquity) }}</div>
        <div class="asset-meta">
          <div>可用 {{ fmt(overview.asset.available) }}</div>
          <div>占用 {{ fmt(overview.asset.marginUsed) }}</div>
          <div :class="pnlClass(overview.asset.unrealizedPnl)">
            未实现 {{ fmt(overview.asset.unrealizedPnl) }}
          </div>
        </div>
      </div>
      <div class="actions">
        <van-button class="action-btn primary">充值</van-button>
        <van-button class="action-btn ghost">划转</van-button>
        <van-button class="action-btn ghost">交易</van-button>
      </div>
    </header>

    <section class="section">
      <div class="section-title">
        <span>热门合约</span>
        <span class="section-sub">24h</span>
      </div>
      <div class="ticker-list">
        <div class="ticker-row" v-for="item in overview.tickers" :key="item.contractId">
          <div class="ticker-main">
            <div class="symbol">{{ item.symbol }}</div>
            <div class="turnover">成交额 {{ fmt(item.turnover24h) }}</div>
          </div>
          <div class="ticker-price">
            <div class="price">{{ fmt(item.lastPrice) }}</div>
            <div :class="pnlClass(item.change24h)">
              {{ pct(item.change24h) }}
            </div>
          </div>
        </div>
        <div v-if="overview.tickers.length === 0" class="empty">暂无行情数据</div>
      </div>
    </section>

    <section class="section">
      <div class="section-title">
        <span>持仓</span>
        <span class="section-sub">逐仓</span>
      </div>
      <div class="position-list">
        <div class="position-row" v-for="pos in overview.positions" :key="pos.contractId">
          <div class="position-main">
            <div class="symbol">{{ pos.symbol }}</div>
            <div class="side" :class="sideClass(pos.side)">
              {{ pos.side === 2 ? '多' : '空' }} · {{ pos.leverage }}x
            </div>
          </div>
          <div class="position-meta">
            <div>仓位 {{ fmt(pos.qty) }}</div>
            <div>开仓 {{ fmt(pos.entryPrice) }}</div>
          </div>
          <div class="position-pnl" :class="pnlClass(pos.unrealizedPnl)">
            {{ fmt(pos.unrealizedPnl) }}
          </div>
        </div>
        <div v-if="overview.positions.length === 0" class="empty">暂无持仓</div>
      </div>
    </section>

    <section class="section">
      <div class="section-title">
        <span>公告</span>
        <span class="section-sub">最新</span>
      </div>
      <div class="announcement-list">
        <div class="announcement-row" v-for="item in overview.announcements" :key="item.id">
          <div class="announcement-title">{{ item.title }}</div>
          <div class="announcement-time">{{ item.publishTime }}</div>
        </div>
        <div v-if="overview.announcements.length === 0" class="empty">暂无公告</div>
      </div>
    </section>

    <section class="section">
      <div class="section-title">
        <span>账户</span>
        <span class="section-sub">安全</span>
      </div>
      <van-button class="action-btn danger" block @click="onLogout">退出登录</van-button>
    </section>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/store/user';
import { getHomeOverview, type HomeOverview } from '@/api/home';

const router = useRouter();
const userStore = useUserStore();

const overview = reactive<HomeOverview>({
  asset: {
    asset: 'USDT',
    totalEquity: 0,
    available: 0,
    marginUsed: 0,
    unrealizedPnl: 0
  },
  tickers: [],
  positions: [],
  announcements: []
});

onMounted(async () => {
  try {
    const res = await getHomeOverview();
    if (res.data) {
      Object.assign(overview, res.data);
    }
  } catch {
  }
});

function fmt(value?: number) {
  if (value === undefined || value === null) {
    return '--';
  }
  return Number(value).toLocaleString('en-US', { maximumFractionDigits: 6 });
}

function pct(value?: number) {
  if (value === undefined || value === null) {
    return '--';
  }
  const num = Number(value) * 100;
  const sign = num > 0 ? '+' : '';
  return `${sign}${num.toFixed(2)}%`;
}

function pnlClass(value?: number) {
  if (value === undefined || value === null) {
    return 'neutral';
  }
  return Number(value) >= 0 ? 'up' : 'down';
}

function sideClass(side?: number) {
  return side === 2 ? 'long' : 'short';
}

function onLogout() {
  userStore.logout();
  router.replace('/login');
}
</script>

<style scoped>
.page {
  min-height: 100vh;
  padding: 18px;
  background: radial-gradient(1200px 600px at 20% -20%, #2b1b0e, transparent),
    radial-gradient(900px 600px at 100% 0%, #0f1b2c, transparent),
    #0b0e11;
  color: #eaecef;
}
.hero {
  display: flex;
  flex-direction: column;
  gap: 16px;
}
.brand {
  display: flex;
  align-items: center;
  gap: 12px;
}
.logo {
  width: 42px;
  height: 42px;
  border-radius: 12px;
  background: linear-gradient(135deg, #f0b90b, #c99400);
  color: #0b0e11;
  font-weight: 700;
  display: grid;
  place-items: center;
}
.title {
  font-size: 18px;
  font-weight: 600;
}
.subtitle {
  font-size: 12px;
  color: #848e9c;
}
.asset-card {
  background: #1e2329;
  border-radius: 16px;
  padding: 16px;
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.35);
}
.asset-label {
  font-size: 12px;
  color: #848e9c;
}
.asset-value {
  font-size: 28px;
  font-weight: 700;
  margin: 8px 0;
}
.asset-meta {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  font-size: 12px;
  color: #b7bdc6;
}
.actions {
  display: flex;
  gap: 10px;
}
.action-btn {
  flex: 1;
  border-radius: 12px;
  font-weight: 600;
  border: none;
  height: 40px;
}
.action-btn.primary {
  background: linear-gradient(135deg, #f0b90b, #c99400);
  color: #0b0e11;
}
.action-btn.ghost {
  background: #1e2329;
  color: #eaecef;
}
.action-btn.danger {
  background: #2b3139;
  color: #f6465d;
}
.section {
  margin-top: 22px;
}
.section-title {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 12px;
}
.section-sub {
  color: #848e9c;
  font-weight: 400;
}
.ticker-list,
.position-list,
.announcement-list {
  background: #14181d;
  border-radius: 14px;
  padding: 8px 12px;
}
.ticker-row,
.position-row,
.announcement-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px solid #1f242b;
}
.ticker-row:last-child,
.position-row:last-child,
.announcement-row:last-child {
  border-bottom: none;
}
.ticker-main .symbol,
.position-main .symbol {
  font-weight: 600;
}
.turnover {
  font-size: 12px;
  color: #848e9c;
}
.ticker-price {
  text-align: right;
}
.price {
  font-weight: 600;
}
.position-meta {
  font-size: 12px;
  color: #848e9c;
}
.position-pnl {
  font-weight: 700;
}
.announcement-title {
  font-size: 13px;
}
.announcement-time {
  font-size: 12px;
  color: #848e9c;
}
.empty {
  padding: 14px 0;
  text-align: center;
  color: #5e6673;
  font-size: 12px;
}
.up {
  color: #0ecb81;
}
.down {
  color: #f6465d;
}
.neutral {
  color: #848e9c;
}
.long {
  color: #0ecb81;
}
.short {
  color: #f6465d;
}
</style>
