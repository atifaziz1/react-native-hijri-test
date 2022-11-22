// import { NativeModules, Platform } from 'react-native';

// const LINKING_ERROR =
//   `The package 'react-native-hijri-test' doesn't seem to be linked. Make sure: \n\n` +
//   Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
//   '- You rebuilt the app after installing the package\n' +
//   '- You are not using Expo Go\n';

// const HijriTest = NativeModules.HijriTest
//   ? NativeModules.HijriTest
//   : new Proxy(
//       {},
//       {
//         get() {
//           throw new Error(LINKING_ERROR);
//         },
//       }
//     );

// export function multiply(a: number, b: number): Promise<number> {
//   return HijriTest.multiply(a, b);
// }
import { NativeModules } from 'react-native';

const { RNHijriDatePickerAndroid } = NativeModules;
console.log('\n\n\n\n**************insideAPP2*****************\n\n\n\n\n');
console.log(RNHijriDatePickerAndroid);
console.log('\n\n\n\n***************insideAPP2****************\n\n\n\n\n');
export default RNHijriDatePickerAndroid;
