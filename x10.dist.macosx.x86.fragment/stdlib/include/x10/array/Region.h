#ifndef __X10_ARRAY_REGION_H
#define __X10_ARRAY_REGION_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERABLE_H_NODEPS
#include <x10/lang/Iterable.h>
#undef X10_LANG_ITERABLE_H_NODEPS
namespace x10 { namespace array { 
class Point;
} } 
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace array { 
class EmptyRegion;
} } 
namespace x10 { namespace compiler { 
class TempNoInline_0;
} } 
namespace x10 { namespace array { 
class FullRegion;
} } 
namespace x10 { namespace array { 
class PolyMatBuilder;
} } 
namespace x10 { namespace array { 
class PolyRow;
} } 
namespace x10 { namespace array { 
class PolyMat;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace array { 
class PolyRegion;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class IllegalArgumentException;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class RectRegion;
} } 
namespace x10 { namespace compiler { 
class TempNoInline_3;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace array { 
class Dist;
} } 
namespace x10 { namespace array { 

class Region : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(rank);
    
    x10_boolean FMGL(rect);
    
    x10_boolean FMGL(zeroBased);
    
    x10_boolean FMGL(rail);
    
    void _instance_init();
    
    static x10aux::ref<x10::array::Region> makeEmpty(x10_int rank);
    static x10aux::ref<x10::array::Region> makeFull(x10_int rank);
    static x10aux::ref<x10::array::Region> makeUnit();
    static x10aux::ref<x10::array::Region> makeHalfspace(x10aux::ref<x10::array::Point> normal,
                                                         x10_int k);
    static x10aux::ref<x10::array::Region> makeRectangularPoly(x10aux::ref<x10::array::Array<x10_int> > minArg,
                                                               x10aux::ref<x10::array::Array<x10_int> > maxArg);
    static x10aux::ref<x10::array::Region> makeRectangular(
      x10aux::ref<x10::lang::Rail<x10_int > > minArg,
      x10aux::ref<x10::lang::Rail<x10_int > > maxArg);
    static x10aux::ref<x10::array::Region> makeRectangular(
      x10aux::ref<x10::array::Array<x10_int> > minArg,
      x10aux::ref<x10::array::Array<x10_int> > maxArg);
    static x10aux::ref<x10::array::Region> makeRectangular(
      x10_int min,
      x10_int max);
    static x10aux::ref<x10::array::Region> make(x10_int min,
                                                x10_int max);
    static x10aux::ref<x10::array::Region> make(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Region> > > regions);
    static x10aux::ref<x10::array::Region> makeBanded(x10_int size,
                                                      x10_int upper,
                                                      x10_int lower);
    static x10aux::ref<x10::array::Region> makeBanded(x10_int size);
    static x10aux::ref<x10::array::Region> makeUpperTriangular(
      x10_int size);
    static x10aux::ref<x10::array::Region> makeUpperTriangular(
      x10_int rowMin,
      x10_int colMin,
      x10_int size);
    static x10aux::ref<x10::array::Region> makeLowerTriangular(
      x10_int size);
    static x10aux::ref<x10::array::Region> makeLowerTriangular(
      x10_int rowMin,
      x10_int colMin,
      x10_int size);
    virtual x10_int size() = 0;
    virtual x10_boolean isConvex() = 0;
    virtual x10_boolean isEmpty() = 0;
    virtual x10_int indexOf(x10aux::ref<x10::array::Point> id__74) = 0;
    virtual x10_int indexOf(x10_int i0);
    virtual x10_int indexOf(x10_int i0, x10_int i1);
    virtual x10_int indexOf(x10_int i0, x10_int i1, x10_int i2);
    virtual x10_int indexOf(x10_int i0, x10_int i1, x10_int i2,
                            x10_int i3);
    virtual x10aux::ref<x10::array::Region> boundingBox();
    virtual x10aux::ref<x10::array::Region> computeBoundingBox(
      ) = 0;
    virtual x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> >
      min(
      ) = 0;
    virtual x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> >
      max(
      ) = 0;
    virtual x10_int min(x10_int i);
    virtual x10_int max(x10_int i);
    virtual x10aux::ref<x10::array::Region> intersection(
      x10aux::ref<x10::array::Region> that) = 0;
    virtual x10_boolean disjoint(x10aux::ref<x10::array::Region> that);
    virtual x10aux::ref<x10::array::Region> product(x10aux::ref<x10::array::Region> that) = 0;
    virtual x10aux::ref<x10::array::Region> translate(x10aux::ref<x10::array::Point> v) = 0;
    virtual x10aux::ref<x10::array::Region> projection(x10_int axis) = 0;
    virtual x10aux::ref<x10::array::Region> eliminate(x10_int axis) = 0;
    virtual x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::array::Point> > >
      iterator(
      ) = 0;
    static x10aux::ref<x10::array::Region> __implicit_convert(
      x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Region> > > a);
    virtual x10aux::ref<x10::array::Region> __and(x10aux::ref<x10::array::Region> that);
    virtual x10aux::ref<x10::array::Region> __times(x10aux::ref<x10::array::Region> that);
    virtual x10aux::ref<x10::array::Region> __plus(x10aux::ref<x10::array::Point> v);
    virtual x10aux::ref<x10::array::Region> __inv_plus(x10aux::ref<x10::array::Point> v);
    virtual x10aux::ref<x10::array::Region> __minus(x10aux::ref<x10::array::Point> v);
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> that);
    virtual x10_boolean contains(x10aux::ref<x10::array::Region> that) = 0;
    virtual x10_boolean contains(x10aux::ref<x10::array::Point> p) = 0;
    virtual x10_boolean contains(x10_int i);
    virtual x10_boolean contains(x10_int i0, x10_int i1);
    virtual x10_boolean contains(x10_int i0, x10_int i1, x10_int i2);
    virtual x10_boolean contains(x10_int i0, x10_int i1, x10_int i2,
                                 x10_int i3);
    void _constructor(x10_int r, x10_boolean t, x10_boolean z);
    
    virtual x10aux::ref<x10::array::Dist> __arrow(x10::lang::Place p);
    virtual x10_boolean __inv_in(x10aux::ref<x10::array::Point> p);
    x10_int rank();
    x10_boolean rect();
    x10_boolean zeroBased();
    x10_boolean rail();
    virtual x10aux::ref<x10::array::Region> x10__array__Region____x10__array__Region__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_REGION_H

namespace x10 { namespace array { 
class Region;
} } 

#ifndef X10_ARRAY_REGION_H_NODEPS
#define X10_ARRAY_REGION_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterable.h>
#include <x10/array/Point.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#include <x10/array/EmptyRegion.h>
#include <x10/compiler/TempNoInline_0.h>
#include <x10/array/FullRegion.h>
#include <x10/array/PolyMatBuilder.h>
#include <x10/array/PolyRow.h>
#include <x10/array/PolyMat.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/array/PolyRegion.h>
#include <x10/array/Array.h>
#include <x10/lang/IllegalArgumentException.h>
#include <x10/lang/String.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/RectRegion.h>
#include <x10/compiler/TempNoInline_3.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Array.h>
#include <x10/lang/Any.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Place.h>
#include <x10/array/Dist.h>
#ifndef X10_ARRAY_REGION_H_GENERICS
#define X10_ARRAY_REGION_H_GENERICS
#endif // X10_ARRAY_REGION_H_GENERICS
#endif // __X10_ARRAY_REGION_H_NODEPS
