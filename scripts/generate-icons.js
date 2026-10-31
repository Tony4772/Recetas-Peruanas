import sharp from 'sharp';
import fs from 'fs';
import path from 'path';

const outDir = path.resolve('public');
if (!fs.existsSync(outDir)) {
  fs.mkdirSync(outDir, { recursive: true });
}

// Andean Culinary SVG logo design
// Beautiful Andean ceramic pot / Sun of Echenique / Choclo & Ají emblem
function getSvg(size, isMaskable = false) {
  // If maskable, safe zone requires content within 80% diameter circle (padding of ~15-20%)
  const padding = isMaskable ? size * 0.18 : size * 0.08;
  const contentSize = size - padding * 2;
  const center = size / 2;

  // Andean Sun / Clay Pot / Ají motif
  return `
  <svg width="${size}" height="${size}" viewBox="0 0 ${size} ${size}" xmlns="http://www.w3.org/2000/svg">
    <defs>
      <linearGradient id="bgGrad" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stop-color="#8D4B00" />
        <stop offset="60%" stop-color="#B45309" />
        <stop offset="100%" stop-color="#C2410C" />
      </linearGradient>
      <linearGradient id="goldGrad" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stop-color="#FDE68A" />
        <stop offset="50%" stop-color="#F59E0B" />
        <stop offset="100%" stop-color="#D97706" />
      </linearGradient>
      <radialGradient id="sunGlow" cx="50%" cy="50%" r="50%">
        <stop offset="0%" stop-color="#FEF3C7" stop-opacity="0.3" />
        <stop offset="100%" stop-color="#FEF3C7" stop-opacity="0" />
      </radialGradient>
      <filter id="shadow" x="-10%" y="-10%" width="120%" height="120%">
        <feDropShadow dx="0" dy="4" stdDeviation="6" flood-opacity="0.3" flood-color="#000000" />
      </filter>
    </defs>

    <!-- Background -->
    ${isMaskable ? `<rect width="${size}" height="${size}" fill="url(#bgGrad)" />` : `<rect width="${size}" height="${size}" rx="${size * 0.22}" fill="url(#bgGrad)" />`}

    <!-- Sun rays ring in background -->
    <circle cx="${center}" cy="${center}" r="${contentSize * 0.44}" fill="url(#sunGlow)" />

    <!-- Group for icon content centered -->
    <g transform="translate(${center}, ${center}) scale(${contentSize / 200})" filter="url(#shadow)">
      <!-- Andean Geometric Ring (Inti / Chakana steps) -->
      <circle cx="0" cy="0" r="90" fill="none" stroke="url(#goldGrad)" stroke-width="4" stroke-dasharray="16 8" opacity="0.6" />
      <circle cx="0" cy="0" r="78" fill="#1C1005" opacity="0.25" />

      <!-- Traditional Clay Cooking Pot (Olla de Barro) -->
      <!-- Pot Handles -->
      <path d="M -54 -12 C -68 -12 -70 12 -54 18" fill="none" stroke="url(#goldGrad)" stroke-width="6" stroke-linecap="round" />
      <path d="M 54 -12 C 68 -12 70 12 54 18" fill="none" stroke="url(#goldGrad)" stroke-width="6" stroke-linecap="round" />

      <!-- Pot Body -->
      <path d="M -48 -10 Q 0 -16 48 -10 Q 56 36 28 58 Q 0 66 -28 58 Q -56 36 -48 -10 Z" fill="#F8FAFC" />
      <!-- Inner pot shading / stew warmth -->
      <path d="M -42 -4 Q 0 -8 42 -4 Q 48 32 24 50 Q 0 58 -24 50 Q -48 32 -42 -4 Z" fill="url(#goldGrad)" opacity="0.95" />

      <!-- Pot Rim -->
      <ellipse cx="0" cy="-10" rx="48" ry="10" fill="#FFFBEB" stroke="#B45309" stroke-width="3" />

      <!-- Aroma Steam curves -->
      <path d="M -16 -22 C -24 -36 -8 -44 -16 -58" fill="none" stroke="#FEF3C7" stroke-width="4" stroke-linecap="round" opacity="0.85" />
      <path d="M 0 -26 C 8 -42 -8 -50 0 -66" fill="none" stroke="#FEF3C7" stroke-width="5" stroke-linecap="round" opacity="0.95" />
      <path d="M 16 -22 C 24 -36 8 -44 16 -58" fill="none" stroke="#FEF3C7" stroke-width="4" stroke-linecap="round" opacity="0.85" />

      <!-- Ají Amarillo / Chef Hat accent in center of pot -->
      <!-- Chili shape -->
      <path d="M 0 12 C 14 18 20 34 6 44 C -6 38 -4 24 0 12 Z" fill="#DC2626" />
      <path d="M 2 12 Q 4 8 0 4" fill="none" stroke="#15803D" stroke-width="3" stroke-linecap="round" />

      <!-- Subtle star / Inti spark -->
      <path d="M 0 -72 L 3 -62 L 13 -62 L 5 -56 L 8 -46 L 0 -52 L -8 -46 L -5 -56 L -13 -62 L -3 -62 Z" fill="#FDE68A" />
    </g>
  </svg>
  `;
}

async function run() {
  const svg512 = getSvg(512, false);
  const svgMaskable512 = getSvg(512, true);
  const svg192 = getSvg(192, false);
  const svg180 = getSvg(180, false);

  fs.writeFileSync(path.join(outDir, 'favicon.svg'), getSvg(128, false));

  await sharp(Buffer.from(svg512))
    .png()
    .toFile(path.join(outDir, 'pwa-512x512.png'));
  console.log('Created pwa-512x512.png');

  await sharp(Buffer.from(svgMaskable512))
    .png()
    .toFile(path.join(outDir, 'pwa-maskable-512x512.png'));
  console.log('Created pwa-maskable-512x512.png');

  await sharp(Buffer.from(svg192))
    .png()
    .toFile(path.join(outDir, 'pwa-192x192.png'));
  console.log('Created pwa-192x192.png');

  await sharp(Buffer.from(svg180))
    .png()
    .toFile(path.join(outDir, 'apple-touch-icon.png'));
  console.log('Created apple-touch-icon.png');

  console.log('All icons generated successfully!');
}

run().catch(console.error);
