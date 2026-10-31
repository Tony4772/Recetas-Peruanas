---
name: Andean Culinary Material
colors:
  surface: '#fef9f1'
  surface-dim: '#ded9d2'
  surface-bright: '#fef9f1'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f8f3eb'
  surface-container: '#f2ede5'
  surface-container-high: '#ece8e0'
  surface-container-highest: '#e7e2da'
  on-surface: '#1d1c17'
  on-surface-variant: '#554336'
  inverse-surface: '#32302b'
  inverse-on-surface: '#f5f0e8'
  outline: '#887364'
  outline-variant: '#dbc2b0'
  surface-tint: '#904d00'
  primary: '#8d4b00'
  on-primary: '#ffffff'
  primary-container: '#b15f00'
  on-primary-container: '#fffbff'
  inverse-primary: '#ffb77d'
  secondary: '#ac3400'
  on-secondary: '#ffffff'
  secondary-container: '#fd6b36'
  on-secondary-container: '#5d1900'
  tertiary: '#7940a9'
  on-tertiary: '#ffffff'
  tertiary-container: '#945ac4'
  on-tertiary-container: '#fffbff'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#ffdcc3'
  primary-fixed-dim: '#ffb77d'
  on-primary-fixed: '#2f1500'
  on-primary-fixed-variant: '#6e3900'
  secondary-fixed: '#ffdbd0'
  secondary-fixed-dim: '#ffb59d'
  on-secondary-fixed: '#390c00'
  on-secondary-fixed-variant: '#832600'
  tertiary-fixed: '#f2daff'
  tertiary-fixed-dim: '#e0b6ff'
  on-tertiary-fixed: '#2d004f'
  on-tertiary-fixed-variant: '#632892'
  background: '#fef9f1'
  on-background: '#1d1c17'
  surface-variant: '#e7e2da'
typography:
  display-lg:
    fontFamily: Epilogue
    fontSize: 57px
    fontWeight: '700'
    lineHeight: 64px
    letterSpacing: -0.25px
  headline-lg:
    fontFamily: Epilogue
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
  headline-lg-mobile:
    fontFamily: Epilogue
    fontSize: 28px
    fontWeight: '700'
    lineHeight: 36px
  headline-md:
    fontFamily: Epilogue
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  headline-sm:
    fontFamily: Epilogue
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  title-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 22px
    fontWeight: '600'
    lineHeight: 28px
  title-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
    letterSpacing: 0.15px
  body-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
    letterSpacing: 0.5px
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
    letterSpacing: 0.25px
  label-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.1px
  label-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
    letterSpacing: 0.5px
  label-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.5px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  gutter-tablet: 1.5rem
  margin: 1rem
  margin-tablet: 2rem
  margin-desktop: 3rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

## Brand & Style

This design system blends Android Material Design 3 (Expressive) with the culinary spirit of Peru. The personality is warm, artisanal, inviting, and vibrant without descending into visual clutter or folkloric cliché. The interface treats food photography and recipe workflows as sacred cultural artifacts, balancing functional utility (timer views, step-by-step cooking modes, ingredient ratios) with an appetizing, earthy sensory atmosphere.

The style unifies:
- **Expressive Material 3**: Systematic tonal containers, clear accessibility hierarchies, dynamic interaction states, and fluid shape morphology.
- **Tactile Warmth & Organic Modernism**: Warm cream and parchment surface foundations instead of sterile cool greys, accompanied by soft warm-tinted ambient diffusion.
- **Gastronomic Vitality**: Intentional pairing of earthy spice pigments (ají amarillo, anticucho terracotta, maíz morado, and huacatay herb green) to guide visual cadence and appetite appeal.

## Colors

The palette draws directly from native Peruvian biodiversity and traditional culinary ingredients:
- **Primary (`#D97706` Ají Amarillo Gold)**: Serves as the central brand beacon, floating action buttons (FAB), recipe primary actions, and hero highlights. Supported by `#F59E0B` for active states and high-visibility star ratings.
- **Secondary (`#C2410C` Anticucho Rust / Terracotta)**: Used for cooking alerts, heat/spice-level indicators, tags for braised/grilled categories, and bold visual anchors. Supported by `#9A3412` for pressed states and high-contrast text links.
- **Tertiary (`#581C87` Maíz Morado Deep Purple)**: Used for regional heritage badges, festive celebratory dishes, cocktail/dessert categorization, and premium cultural narratives.
- **Herb Accent (`#15803D` Huacatay / Cilantro Green)**: Used contextually for dietary tags (vegetarian, gluten-free, organic fresh herbs), prep complete indicators, and success affirmations.
- **Neutral Foundations (`#FDFBF7` Canvas, `#F5F0E8` Surface Container, `#292524` Earth Ink)**: Replaces cold tech grays with sun-baked parchment tones. Text is anchored in `#292524` to retain high legibility while harmonizing with warm culinary tones.

## Typography

The type system blends structural geometry with human warmth.
- **Headlines & Display (Epilogue)**: Imparts distinctive editorial character, reminiscent of hand-crafted gastronomic journals and woodblock posters, while preserving modern geometric clarity.
- **Body & Labels (Plus Jakarta Sans)**: Delivers clear legibility for instructional text, measurement quantities, ingredient lists, and small chip labels. Its soft terminals echo the warm aesthetic without sacrificing systematic rhythm.
- For active cooking view screens, step timers and ingredient proportions prioritize `title-lg` and `body-lg` to ensure effortless glanceability at arm's length.

## Layout & Spacing

The layout follows an adaptive Material 3 grid built upon an 8dp core rhythm (with 4dp sub-steps for micro-alignments):
- **Mobile (<600dp)**: 4-column fluid layout with `16px` (`margin`) outer padding and `16px` (`gutter`) column spacing. Bottom navigation bar and dynamic pill-shaped extended FAB anchoring key flows.
- **Tablet (600dp–840dp)**: 8-column layout with `32px` (`margin-tablet`) margins and `24px` (`gutter-tablet`) gutters. Transition to a permanent navigation rail on the left and two-pane recipe details (ingredients on left, interactive step instructions on right).
- **Desktop / Foldable Expanded (>840dp)**: 12-column layout max-width centered at 1200px, utilizing multi-card discovery grids and dedicated prep dashboard sidebars.

## Elevation & Depth

Visual hierarchy aligns with Material Design 3 surface container tiers, rendered with warm ambient undertones:
- **Surface Levels**:
  - `Surface`: Base canvas `#FDFBF7`.
  - `Surface Container Low`: `#F9F5EE` for nested collection sections.
  - `Surface Container`: `#F5F0E8` for inactive cards and input containers.
  - `Surface Container High`: `#EDE6DC` for elevated recipe cards and modal bottom sheets.
  - `Surface Container Highest`: `#E4DC handle` for selected states and active ingredient rows.
- **Ambient Shadows**: Shadows do not use harsh neutral grey. They are diffused with warm umber undertones (`rgba(41, 37, 36, 0.08)` to `rgba(41, 37, 36, 0.14)`) combined with a soft blur radius (8px to 24px) to simulate natural Andean sunlight filtering across surfaces.
- **Scrims**: Dark overlays for culinary bottom sheets and photo overlays use `#292524` with 60% opacity rather than pure black.

## Shapes

The shape hierarchy establishes an organic, friendly Material 3 personality:
- **Extra Large (`28px` to `32px`)**: Applied to hero food cards, featured regional carousels, and persistent modal bottom sheets.
- **Large (`16px` to `24px`)**: Standard recipe feed cards, cooking step containers, and visual ingredient groups.
- **Medium (`12px` to `16px`)**: Text fields, quantity steppers, and dialog modules.
- **Full Pill (`9999px`)**: Interactive filter chips, dietary tags, floating action buttons (FABs), and primary CTA buttons.

## Components

### Buttons
- **Primary CTA ("Start Cooking")**: Fully pill-shaped (`9999px`), background in `#D97706` (Ají Gold), label in `#FFFFFF` (`label-lg`), with subtle warm ambient shadow. On press, transitions to `#B45309`.
- **Secondary Action**: Pill-shaped with a 1.5px border in `#C2410C` (Terracotta) and transparent fill; text styled in `#C2410C`.
- **Floating Action Button (FAB)**: Material 3 container with `24px` rounded corner or pill for extended FAB, dressed in `#D97706` with high-contrast icon.

### Recipe Cards
- **Feed Cards**: Rounded with `24px` radius (`rounded-2xl`), utilizing `Surface Container` (`#F5F0E8`) with no harsh outline. Featured recipe images bleed edge-to-edge at top with an organic 16:9 or 4:3 ratio. Meta tags (cook time, difficulty, spice level) sit on pill tags overlaying the image bottom.
- **Active Step Cards**: Surface container styled with `20px` radius, highlighted with an internal 2px accent indicator in `#D97706` when active in cooking mode.

### Chips & Filter Tags
- **Filter Chips**: Pill-shaped (`rounded-full`), height of 36px, `Surface Container High` (`#EDE6DC`) background, `#292524` text.
- **Selected State**: Background fills with `#C2410C` (Rust) or `#581C87` (Maíz Morado) with contrasting `#FFFFFF` text and a leading checkmark icon.

### Checkboxes & Ingredient Lists
- **Ingredient Row**: Structured with `space-md` vertical padding. Interactive circular check targets (`24px`) that toggle from a warm outline `#A8A29E` to a filled `#15803D` (Herb Green) check on item completion, applying a strikethrough and 60% opacity to completed text.

### Form Inputs & Search Fields
- **Search Bar**: Fully rounded pill shape (`9999px`), container in `#F5F0E8`, leading search icon in `#78716C`, hint text in Plus Jakarta Sans 15px.
- **Quantity Stepper**: Pill-shaped segmented container with `#EDE6DC` background, featuring tactile plus/minus icon touch targets for adjusting serving sizes.

### Additional Domain Components
- **Ají Heat Gauge**: Micro-component displaying 1 to 5 miniature ají pepper glyphs filled progressively with `#C2410C` to communicate spiciness.
- **Kitchen Timer Pill**: Compact floating pill displaying active cooking countdowns with maíz morado (`#581C87`) pulsing progress track.